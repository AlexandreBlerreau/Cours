#include <sys/types.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <time.h>
#include "socket.h"
#include "prototypes.h"

#define NB_MIMETYPE 38

// strucutre pour stocker l'extension d'un fichier et son type {.png,image/png}
typedef struct {
    char *extension;
    char *typeFichier;
} ExtensionType;

// liste des écoutes
int list_s;

// liste des extensions...
ExtensionType extensionFichier [] = {
    {".css", "text/css"},
    {".gif", "image/gif"},
    {".htm", "text/html"},
    {".html","text/html"},
    {".jpeg","image/jpeg"},
    {".jpg", "image/jpeg"},
    {".png", "image/png"},
    {".ico", "image/x-icon"},
    {".js",  "application/javascript"},
    {".pdf", "application/pdf"},
    {".mp4", "video/mp4"},
    {".png", "image/png"},
    {".svg", "image/svg+xml"},
    {".xml", "text/xml"},
    {".json","application/json"},
    {".tiff","image/tiff"},
    {".tif", "image/tiff"},
    {".svgz","image/svg+xml"},
    {".bmp", "image/bmp"},
    {".zip", "application/zip"},
    {".rar", "application/x-rar-compressed"},
    {".exe", "application/x-msdownload"},
    {".msi", "application/x-msdownload"},
    {".cab", "application/vnd.ms-cab-compressed"},
    {".mp3", "audio/mpeg"},
    {".qt",  "video/quicktime"},
    {".mov", "video/quicktime"},
    {".psd", "image/vnd.adobe.photoshop"},
    {".ai",  "application/postscript"},
    {".eps", "application/postscript"},
    {".ps",  "application/postscript"},
    {".doc", "application/msword"},
    {".rtf", "application/rtf"},
    {".xls", "application/vnd.ms-excel"},
    {".pptx", "application/vnd.ms-powerpoint"},
    {".odt", "application/vnd.oasis.opendocument.text"},
    {".ods", "application/vnd.oasis.opendocument.spreadsheet"},
    {NULL, NULL},
};

// La racine est l'endroit ou le serveur est executé
char* root;
// par defaut 8080 peut être changer avec -p
int PORT = 8080;
// FD pour les sockets
int socketClient = -1, socketServeur = -1;

//retourne la taille d'un fichier
int get_size_of(char * filename){
    struct stat st;
    int size;

    stat(filename, &st);
    size = st.st_size;
    
    return size;
}

//retourne l'extension d'un fichier
static char* getExtension(char *filename)
{
    //cherche l'endroit du .Extension
    char *point = strrchr(filename, '.');
    
    if(point != NULL){
        int i;
        for(i = 0; i < NB_MIMETYPE; i++){
            if(strcmp(point,extensionFichier[i].extension) == 0){
                return extensionFichier[i].typeFichier;
            }
        }
    }
    return NULL;
}


// traitement de signaux
void traitement_signal(int sig)
{
    printf("Signal %d reçu\n", sig);
    waitpid(-1,NULL,WNOHANG);
}


// initialisation des signaux
void initialiser_signaux(void)
{
    struct sigaction sa;
    sa.sa_handler = traitement_signal;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_RESTART;
    
    //Corrige le crash quand l'utilisateur se deco avant le message de bienvenue
    if(signal(SIGPIPE , SIG_IGN) == SIG_ERR){
        perror("signal");
    }
    if (sigaction(SIGCHLD , &sa, NULL) == -1){
        perror("sigaction(SIGCHLD)");
    }
}

/*Methode identique a subString en java*/
char *substr(char *src,int pos,int len)
{
    char *dest=NULL;
    if (len>0) {
        dest = (char *) malloc(len+1);
        if (dest == NULL) {
            perror("erreur malloc");
            stop();
        }
        strncat(dest,src+pos,len);
    }
    return dest;
}

// Arrêt
void stop(void)
{
    printf("\033[32m");
    printf("Arrêt du serveur\n");
    printf("\033[39m");
    
    if (root != NULL){
        free(root);
    }
    // Liberation de la socket
    if (socketServeur != -1){
        close(socketServeur);
    }
    // Arret
    exit(0);
}


// recupère le header client
char *GET(int fd)
{
    FILE *f;
    // ouvertre de la socket
    if( (f = fdopen(fd, "r")) == NULL){
        perror("file decriptor");
        stop();
    }
    size_t size = 1;
    char *block;
    if( (block = malloc(sizeof(char) * size)) == NULL ){
        perror("mémoire block");
        stop();
    }
    *block = '\0';
    char *tmp;
    if( (tmp = malloc(sizeof(char) * size)) == NULL ){
        perror("malloc tmp");
        stop();
    }
    *tmp = '\0';
    int fin;
    int oldsize = 1;
    while( (fin = getline( &tmp, &size, f)) > 0){
        if( strcmp(tmp, "\r\n") == 0){
            break;
        }
        block = realloc(block, size+oldsize);
        if (block == NULL) {
            perror("malloc");
            stop();
        }
        oldsize += size;
        strcat(block, tmp);
    }
    free(tmp);
    return block;
}

// retourne le chemin vers la ressource demandée
char * getFichier(char* msg)
{
    char * file;
    if( (file = malloc(sizeof(char) * strlen(msg))) == NULL){
        perror("malloc file");
        stop();
    }
    sscanf(msg, "GET %s HTTP/1.1", file);
    char *base;
    if( (base = malloc(sizeof(char) * (strlen(file) + 18))) == NULL){
        perror("malloc base");
        stop();
    }
    char* ph = "www";
    strcpy(base, ph);
    strcat(base, file);
    free(file);
    return base;
}

// parse de ma requête
Req parseRequest(char *msg)
{
    char* file;
    Req req;
    if( (file = malloc(sizeof(char) * strlen(msg))) == NULL){
        perror("malloc file");
        stop();
    }
    file = getFichier(msg);
    char *badstring = "..";
    char *test = strstr(file, badstring);
    
    // Si / alord index.html
    int test2 = strcmp(file, "www/");
    
    // verif http version
    char * test3 = NULL;
    test3 = strstr(msg,"HTTP/1.1");
    
    // verif si le fichier existe et peut être lu
    FILE *exists = fopen(file, "r" );
    
    // fichier incunnu donc erreur 404
    if( test != NULL ){
        req.returncode = 404;
        req.filename = "www/404.html";
        req.header = msg;
    }
    // index est demandé
    else if(test2 == 0){
       req.returncode = 200;
        req.filename = "www/index.html";
        req.header = msg;
    }
     // autre que index
    else if( exists != NULL ){
        req.returncode = 200;
        req.filename = file;
        req.header = msg;
        fclose(exists);
    }
    // autre que index qui existe pas 404
    else if(exists == NULL){
        req.returncode = 404;
        req.filename = "www/404.html";
        req.header = msg;
    }
    // version http non supportée
     if(test3 == NULL){
        req.returncode = 505;
        req.filename = "www/505.html";
        req.header = msg;
    }
    return req;
}

// Ecris le fichier demander dans la socket
int printFile(int fd, char *filename)
{
    // juste de l'affichage
    if(strcmp(filename,"www/404.html") == 0){
        printf("\033[41m");
        printf("%s\n",filename);
        printf("\033[0m");
        printf("   \n");
    }else {
         printf("%s\n",filename);
         printf("   \n");
    }
   
    FILE *fichier;
    fichier = fopen(filename, "r");
    if( (fichier = fopen(filename, "r")) == NULL){
        stop();
    }
    
    // recup taille
    int totalsize;
    struct stat st;
    stat(filename, &st);
    totalsize = st.st_size;
    
    size_t size = 1;
    
    char *temp;
    if(  (temp = malloc(sizeof(char) * size)) == NULL ){
        perror("malloc temp");
        stop();
    }
  
    int fin;
    char buffer[1024];
    
    while( (fin = fread( buffer, 1,1024,fichier)) > 0){
        if(write(fd,buffer,fin) == -1){
            perror("write");
            stop();
        }
    }
    
    fichier = NULL;
    free(temp);
    
    return totalsize;
}

// Ecris le header a envoyer en fonction du fichier qui lui le sera par la suite dans la socket
int EnvoiHeader(int fd,int code,char * filename)
{
    printf("\033[0m");
    char *header5;
    char * header2;
    char *header4;

    // recuperation de l'extension
    char * contentType = NULL;
    contentType = getExtension(filename);
   
    if(code == 200){
        // preparation du head a retourner pour code 200
        header2 = malloc(100);
        if (header2 == NULL) {
            perror("malloc temp");
            stop();
        }
        strcat(header2,"HTTP/1.1 200 OK\r\n");
        strcat(header2,"Content-Type: ");
        strcat(header2,contentType);
        strcat(header2,"\r\n");
        strcat(header2,"Content-Length: ");
        
        char longeur2 [10];
        sprintf(longeur2, "%i", get_size_of(filename));
        
        strcat(header2,longeur2);
        strcat(header2,"\r\n\r\n");
        strcat(header2, "\0");
        header2 = realloc(header2, sizeof(char) * strlen(header2) +31);
    }
    if(code == 404){
        // preparation du head a retourner pour code 404
        header4 = malloc(100);
        if (header4 == NULL) {
            perror("malloc temp");
            stop();
        }
        strcat(header4,"HTTP/1.1 404 Not Found\r\n");
        
        strcat(header4,"Content-Type: ");
        strcat(header4,contentType);
        strcat(header4,"\r\n");
        strcat(header4,"Content-Length: ");
        
        char longeur4 [1000];
        sprintf(longeur4, "%i", get_size_of(filename));
        
        strcat(header4,longeur4);
        strcat(header4,"\r\n\n");
        
        header4 = realloc(header4,strlen(header4) +31);
    }
    if(code == 505){
        // preparation du head a retourner pour code 505
        header5 = malloc(100);
        if (header5 == NULL) {
            perror("malloc temp");
            stop();
        }
        strcat(header5,"HTTP/1.1 200 OK\r\n");
        strcat(header5,"Content-Type: ");
        strcat(header5,contentType);
        strcat(header5,"\r\n");
        strcat(header5,"Content-Length: ");
        
        char longeur5 [1000];
        sprintf(longeur5, "%i", get_size_of(filename));
        
        strcat(header5,longeur5);
        strcat(header5,"\r\n\n");
        header5 = realloc(header5,strlen(header5));
    }
    
    

    // affichage de l'heure dans la console de log
    time_t currenttime;
    char stockage [29];
    struct tm * timeinfo;
    
    time (&currenttime);
    timeinfo = localtime(&currenttime);
    strftime(stockage,21,"[%d-%m-%Y %H:%M:%S]",timeinfo);
    puts(stockage);
    
    switch (code)
    {
        case 200:
            if(write(fd, header2, sizeof(char)*strlen(header2)) == -1){
                perror("write temp");
                stop();
            }
            printf("GET / HTTP 1.1 / 200\t");
            header2 = NULL;
            free(header2);
            return 1;
            break;
            
        case 404:
            if(write(fd, header4, sizeof(char)*strlen(header4)) == -1){
                perror("write temp");
                stop();
            }
            printf("\033[41m");
            printf("GET / HTTP 1.1 / 404\t");
            printf("\033[0m");
            free(header4);
            return 1;
            break;
            
        case 505:
            if(write(fd, header5, sizeof(char)*strlen(header5)) == -1){
                perror("write temp");
                stop();
            }
            printf("\033[41m");
            printf("GET / HTTP ?.? / 505\t");
            printf("\033[0m");
            free(header5);
            return 1;
            break;
        }
    
    perror("code erreur inconnu");
    return -42;
    }

// initialisation du dossier www de 404.html index.html 505.html
void init()
{
    system("clear");
    printf("========= Pawnee Web Serveur =========\n");
    struct stat dossier;
    
    // vérifie si le dossier www existe
    if(stat("www/",&dossier) < 0){
        printf("\033[32m");
        printf("[START]: Le répertoire www est absent ou a été supprimé création...\n");
        printf("\033[39m");
        // création répartoire www
        mkdir("www",S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH);
    }
    else{
        printf("\033[32m");
        printf("[START]: Le répertoir www est prêt\n");
        printf("\033[39m");
    }
    FILE * index = fopen("www/index.html", "r+");
    
    if (index == NULL){
        char * pageIndex = "<html><head><meta charset=\"UTF-8\"><title>Pawnee index</title></head><body><h1>Ca marche!</h1>Le serveur Pawnee tourne!<br /> Vous pouvez modifier cette page dans www/index.html et ajouter vos pages le répertoire</body></html>";
        int nbOuverture;
        printf("\033[32m");
        printf("[START]: Le fichier index.html est absent ou a été supprimé création...\n");
        printf("\033[39m");
        // création index.html
        nbOuverture =open("www/index.html",O_CREAT | O_WRONLY | O_APPEND,0666);
        write(nbOuverture,pageIndex,strlen(pageIndex));
        close(nbOuverture);
    } else {
        printf("\033[32m");
        printf("[START]: Le fichier index.html est prêt\n");
        printf("\033[39m");
    }
    FILE * erreur404 = fopen("www/404.html", "r+");
    if (erreur404 == NULL){
        char * page404 = "<html><head><meta charset=\"UTF-8\"><title>404 Not Found</title></head><body><h1>404 Not Found</h1> L'URL n'a pas été trouvé sur le serveur.</body></html>";
        int nbOuverture;
        printf("\033[32m");
        printf("[START]: Le fichier 404.html est absent ou a été supprimé création...\n");
        printf("\033[39m");
        // création 404.html
        nbOuverture =open("www/404.html",O_CREAT | O_WRONLY | O_APPEND,0666);
        if(write(nbOuverture,page404,strlen(page404)) == -1){
            perror("write temp");
            stop();
        }
        close(nbOuverture);
    } else {
        printf("\033[32m");
        printf("[START]: Le fichier 404.html est prêt\n");
        printf("\033[39m");
    }
    
    FILE * erreur505 = fopen("www/505.html", "r+");
    
    if (erreur505 == NULL){
        char * page505 = "<html><head><meta charset=\"UTF-8\"><title>505 HTTP Version Not Supported</title></head><body><h1>505 HTTP Version Not Supported</h1>La version http n'est pas supportée</body></html>";
        int nbOuverture;
        printf("\033[32m");
        printf("[START]: Le fichier 505.html est absent ou a été supprimé création...\n");
        printf("\033[39m");
        // création 505.html
        nbOuverture =open("www/505.html",O_CREAT | O_WRONLY | O_APPEND,0666);
        if(write(nbOuverture,page505,strlen(page505)) == -1){
            perror("write temp");
            stop();
        }
        close(nbOuverture);
    } else {
        printf("\033[32m");
        printf("[START]: Le fichier 505.html est prêt\n");
        printf("\033[39m");
    }
    
    printf("\033[32m");
    printf("[START]: Le serveur est prêt. En écoute sur le port %i.\n",PORT);
    printf("\033[39m");
    
    printf("\033[34m");
    printf("[INFO]: Vous pouvez changer le port à écouter avec $ ./pawnee -p [port]\n");
    printf("\033[39m\n");
}

int main(int argc, char *argv[])
{
    if(argc > 1){
        int opt;
        while ((opt = getopt(argc, argv, "p:")) != -1){
            switch (opt){
                // -p port
                case 'p':
                    PORT = atoi(optarg);
                    break;
            }
        }
    }
    
    init();
    int conn_s;
    short int port = PORT;
    struct sockaddr_in servaddr;
    
    // chemin racine = dossier ou est le serveur
    root = getenv("PWD");
    
    if ((list_s = creer_serveur(port)) < 0 ) {
        stop();
    }
    
    if( (listen(list_s, 100)) == -1){
        stop();
    }
    
    socklen_t addr_size = sizeof(servaddr);
    
    int children = 0;
    
    pid_t pid;
    // bloucle serveur
    while(1){
        if( children <= 10){
            pid = fork();
            children++;
        }
        if( pid == -1){
            stop();
        }
        if ( pid == 0){
            while(1){
                initialiser_signaux();
                conn_s = accept(list_s, (struct sockaddr *)&servaddr, &addr_size);
                if(conn_s == -1){
                    stop();
                }
                char * header = GET(conn_s);
                Req details = parseRequest(header);
               
                free(header);
                
                EnvoiHeader(conn_s, details.returncode,details.filename);
                printFile(conn_s, details.filename);
                
                // fermeture de la connection
                close(conn_s);
            }
        }
    }
    return 1;
}

