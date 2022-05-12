
#include "kandidat.h"
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

stKandidat* vytvorKandidata(int id, char* jmeno,enum OBOR obor, char* tel, char* mail, char* jazyky)
{
	stKandidat* novyKandidat = (stKandidat*)malloc(sizeof(stKandidat));
	novyKandidat->id = id;
	strcpy(novyKandidat->jmeno, jmeno);
	novyKandidat->obor = obor;
	strcpy(novyKandidat->tel, tel);
	strcpy(novyKandidat->mail, mail);
	strcpy(novyKandidat->jazyky, jazyky);
	novyKandidat->dalsi = NULL;
	return novyKandidat;
}

void vypisKandidata(stKandidat* kandidat)
{
	printf("id=%d, jmeno=%s, obor=%c, tel=%s, mail=%s, jazyky=%s\n", kandidat->id, kandidat->jmeno, kandidat->obor, kandidat->tel, kandidat->mail, kandidat->jazyky);
}

