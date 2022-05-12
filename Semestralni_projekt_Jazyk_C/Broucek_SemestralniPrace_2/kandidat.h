#pragma once
#include "enums.h"
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct kandidat {
	int id;
	char jmeno[50];
	enum OBOR obor;
	char tel[50];
	char mail[50];
	char jazyky[50];
	struct kandidat* dalsi;
}stKandidat;

stKandidat* vytvorKandidata(int id, char* jmeno, enum OBOR obor, char* tel, char* mail, char* jazyky);

void vypisKandidata(stKandidat* kandidat);
