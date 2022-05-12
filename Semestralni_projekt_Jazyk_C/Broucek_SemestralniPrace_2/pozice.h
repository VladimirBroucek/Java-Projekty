#pragma once
#include "enums.h"
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct pozice {
	int id;
	char pozice[50];
	char popis[50];
	char pozadavky[50];
	char nabidka[50];
	char jazyky[50];
	float maxPlat;
	enum KRAJ kraj;
	struct pozice* dalsi;
}stPozice;

stPozice* vytvorPozici(int id, char* pozice,enum KRAJ kraj,char* popis,char* pozadavky, char* nabidka, char* jazyky,float maxPlat);

void vypisPozici(stPozice* pozice);
