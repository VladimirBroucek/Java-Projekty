#pragma once
#include "kandidat.h"
#include "pozice.h"
#include "enums.h"
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct pohovor {
	int id;
	stKandidat* kandidat;
	stPozice* pozice;
	enum VYSLEDEK_POHOVORU vysledek;
}stPohovor;

stPohovor* vytvorPohovor(stKandidat* kandidat, stPozice* idPozice);


void vypisPohovoru(stPohovor* pohovor);
