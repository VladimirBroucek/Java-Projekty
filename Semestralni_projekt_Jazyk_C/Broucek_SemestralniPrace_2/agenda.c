

#include "agenda.h"
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static stKandidat* prvniKandidat = NULL, * aktualniKandidat = NULL;
static stPozice* prvniPozice = NULL, * aktualniPozice = NULL;
static stPohovor** polePohovoru = NULL;

void nactiSeznamKandidat(char* nazevSouboru)
{
	FILE* souborKandidati = fopen(nazevSouboru, "r");
	char str[100];
	char* pom = NULL;
	fgets(str, 100, souborKandidati);

	while (!feof(souborKandidati)) {
		fgets(str, 100, souborKandidati);
	char jmeno[50];
	char email[100];
	char jazyky[50];
	char tel[100];
	enum Obor obor;

		pom = strtok(str, ";");
		int id = atoi(pom);

		pom = strtok(NULL, ";");
		strcpy(jmeno, pom);

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			obor = 0;
		}else {
			obor = pom[0];
		}

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(tel, "");
		}else{
			strcpy(tel, pom);
		}
		

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(email, "");
		}else {
			strcpy(email, pom);
		}
		
		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(jazyky, "");
		}else {
			strcpy(jazyky, pom);
		}
		
		if (prvniKandidat == NULL) {
			prvniKandidat = vytvorKandidata(id, jmeno, obor, tel, email, jazyky);
			aktualniKandidat = prvniKandidat;
		}
		else {
			aktualniKandidat->dalsi = vytvorKandidata(id, jmeno, obor, tel, email, jazyky);
			aktualniKandidat = aktualniKandidat->dalsi;
		}
	}

	fclose(souborKandidati);
}

void nactiSeznamPozic(char* nazevSouboru)
{
	FILE* souborPozic = fopen(nazevSouboru, "r");
	char str[255];
	char* pom = NULL;
	fgets(str, 100, souborPozic);

	while (!feof(souborPozic)) {
		fgets(str, 255, souborPozic);

		char pozice[100];
		char popis[50];
		char pozadavky[50];
		char nabidka[50];
		char jazyky[50];
		enum KRAJ kraj;
		float plat = 0;

		pom = strtok(str, ";");
		int id = atoi(pom);

		pom = strtok(NULL, ";");
		strcpy(pozice, pom);
		

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(popis, "");
		}else{
			strcpy(popis, pom);
		}

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(pozadavky, "");
		}else{
			strcpy(pozadavky, pom);
		}

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(nabidka, "");
		}else{
			strcpy(nabidka, pom);
		}

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			strcpy(jazyky, "");
		}
		else {
			strcpy(jazyky, pom);
		}

		pom = strtok(NULL, ";");	
		if (pom == NULL) {
			plat = 0;
		}else {
			plat = atof(pom);
		}

		pom = strtok(NULL, ";");
		if (pom == NULL) {
			kraj = 0;
		}
		else {
			kraj = pom[0];
		}

		if (prvniPozice == NULL) {
			prvniPozice = vytvorPozici(id, pozice, kraj, popis, pozadavky, nabidka, jazyky, plat);
			aktualniPozice = prvniPozice;
		}else{
			aktualniPozice->dalsi = vytvorPozici(id, pozice, kraj, popis, pozadavky, nabidka, jazyky, plat);
			aktualniPozice = aktualniPozice->dalsi;
		}	
	}
	fclose(souborPozic);
}

void vypisSeznamKandidatu(){
	stKandidat* pom = prvniKandidat;
	while (pom != NULL) {
		vypisKandidata(pom);
		pom = pom->dalsi;
	}
}

void pridejKandidata(stKandidat* kandidat)
{
	if (prvniKandidat == NULL) {
		prvniKandidat = kandidat;
		aktualniKandidat = prvniKandidat;
		aktualniKandidat->dalsi = NULL;
	}else{
		aktualniKandidat->dalsi = kandidat;
		aktualniKandidat = kandidat;
		aktualniKandidat->dalsi = NULL;
	}
}

stKandidat* odeberKandidataZeSeznamu(int cisloKandidata)
{
	stKandidat* pom = prvniKandidat;
	stKandidat* aktualni = prvniKandidat;

	while (aktualni != NULL) {
		if (pom->id == cisloKandidata) {
			pom->dalsi = aktualni->dalsi;

			if (aktualni == prvniKandidat) {
				prvniKandidat = aktualni->dalsi;
			}

			stKandidat* odebranyKandidat = aktualni;
			return odebranyKandidat;

		}

		pom = aktualni;
		aktualni = aktualni->dalsi;

	}
	return NULL;
}

stKandidat* najdiKandidataZeSeznamu(int cisloKandidata){
	stKandidat* pom = prvniKandidat;
	while (pom != NULL) {
		if (pom->id == cisloKandidata) {
			return pom;
		}
		pom = pom->dalsi;
	}
	return NULL;
}

void zrusSeznamKandidatu()
{
	stKandidat* pom = prvniKandidat;
	while (pom != NULL) {
		pom = NULL;
		free(pom);
		pom = pom->dalsi;
	}
	prvniKandidat = NULL;
	aktualniKandidat = NULL;
}

void vypisSeznamPozic(){
	stPozice* pom = prvniPozice;
	while (pom != NULL) {
		vypisPozici(pom);
		pom = pom->dalsi;
	}
}


void pridejPozice(stPozice* pozice)
{
	if (prvniPozice == NULL) {
		prvniPozice = pozice;
		aktualniPozice = prvniPozice;
		aktualniPozice->dalsi = NULL;
	}else{
		aktualniPozice->dalsi = pozice;
		aktualniPozice = pozice;
		aktualniPozice->dalsi = NULL;
	}
}

stPozice* odeberPoziciZeSeznamu(int cisloPozice)
{
	stPozice* pom = prvniPozice;
	stPozice* aktualni = prvniPozice;

	while (aktualni != NULL) {
		if (pom->id == cisloPozice) {
			pom->dalsi = aktualni->dalsi;

			if (aktualni == prvniPozice) {
				prvniPozice = aktualni->dalsi;
			}

			stPozice* odebranaPozice = aktualni;
			return odebranaPozice;

		}
		pom = aktualni;
		aktualni = aktualni->dalsi;
		
	}
	return NULL;
}

stPozice* najdiPoziceZeSeznamu(int cisloPozice)
{
	stPozice* pom = prvniPozice;
	while (pom != NULL) {
		if (pom->id == cisloPozice) {
			return pom;
		}
		pom = pom->dalsi;
	}
	return NULL;
}

void zrusSeznamPozic()
{
	stPozice* pom = prvniPozice;
	while (pom != NULL) {
		pom = NULL;
		free(pom);
		pom = pom->dalsi;
	}
	aktualniPozice = NULL;
	prvniPozice = NULL;
}

void alokujPolePohovoru()
{
	polePohovoru = (stPohovor**)calloc(10, sizeof(stPohovor));
}

void pridejPohovor(stPohovor* pohovor)
{
	for (int i = 0; i < 10; i++) {
		if (polePohovoru[i] == NULL) {
			polePohovoru[i] = pohovor;
			break;
		}
	}
}

void vypisPohovory()
{
	int i = 0;
	for (int i = 0; i < 10; i++) {		
		if (polePohovoru[i] != NULL) {
			vypisPohovoru(polePohovoru[i]);	
		}
	}

}

void zmenStavPohovoru(int id, enum STAV_POHOVORU vysledek)
{
	for (int i = 0; i <	10; i++) {
		if (polePohovoru[i] != NULL) {
			if (polePohovoru[i]->id == id) {

				polePohovoru[i]->vysledek = vysledek;
			}
		}
	}
}

void dealokacePolePohovory() {
	for (int i = 0; i < 10; i++) {
		free(polePohovoru[i]);
	}
	free(polePohovoru);
}



