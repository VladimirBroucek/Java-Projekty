
#include "pohovor.h"
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

int hodnotaId = 0;
stPohovor* vytvorPohovor(stKandidat* kandidat, stPozice* idPozice)
{
	stPohovor* novyPohovor = (stPohovor*)malloc(sizeof(stPohovor));
	novyPohovor->id = hodnotaId++;
	novyPohovor->kandidat = kandidat;
	novyPohovor->pozice = idPozice->pozice;
	novyPohovor->vysledek = nenastaveno;

}


void vypisPohovoru(stPohovor* pohovor)
{
	printf("kandidat=%s, pozice=%s, stav=%c", pohovor->kandidat->jmeno, pohovor->pozice->pozice, pohovor->vysledek);
}


