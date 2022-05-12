
#include "pozice.h"
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

stPozice* vytvorPozici(int id, char* pozice,enum KRAJ kraj, char* popis, char* pozadavky, char* nabidka, char* jazyky, float maxPlat)
{
	stPozice* novaPozice = (stPozice*)malloc(sizeof(stPozice));
	novaPozice->id = id;
	strcpy(novaPozice->pozice, pozice);
	novaPozice->kraj = kraj;
	strcpy(novaPozice->popis, popis);
	strcpy(novaPozice->popis, popis);
	strcpy(novaPozice->nabidka, nabidka);
	strcpy(novaPozice->jazyky, jazyky);
	novaPozice->maxPlat = maxPlat;
	novaPozice->dalsi = NULL;

	return novaPozice;
}

void vypisPozici(stPozice* pozice)
{
	printf("id=%d, pozice=%s, kraj=%c, popis=%s, pozadavky=%s, nabidka=%s, jazyky=%s, plat=%f", pozice->id, pozice->pozice, pozice->kraj, pozice->popis, pozice->pozadavky, pozice->nabidka, pozice->jazyky, pozice->maxPlat);
}
