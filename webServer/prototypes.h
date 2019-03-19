// stock le code retour du navigateur, le retour header du client et le fichier demander par le client
typedef struct {
    int returncode;
    char *filename;
    char * header;
} Req;

// prototyes des fonctions
void traitement_signal(int sig);
void initialiser_signaux(void);
char *substr(char *src,int pos,int len);
void stop(void);
char *GET(int fd);
char * getFichier(char* msg);
Req parseRequest(char *msg);
int printFile(int fd, char *filename);
int EnvoiHeader(int fd,int code, char * filename);
void init();
static char* getExtension(char *filename);
int get_size_of(char * filename);


