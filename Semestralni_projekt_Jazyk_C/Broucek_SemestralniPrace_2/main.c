
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include "agenda.h"

void vypisObsahu() {
	printf("1 - Nacti data do seznamu kandidatu ze souboru CSV\n");
	printf("2 - Nacti data do seznamu pozic ze souboru CSV\n");
	printf("3 - Vypis seznam kandidatu\n");
	printf("4 - Vypis seznam pozic\n");
	printf("5 - Pridej z klavesnice kandidata do seznamu\n");
	printf("6 - Pridej z klavesnice pozici do seznamu\n");
	printf("7 - Odeber kandidata se seznamu\n");
	printf("8 - Odeber pozici se seznamu\n");
	printf("9 - Zrus seznam kandidatu/pozic\n");
	printf("10 - Najdi kandidata\n");
	printf("11 - Najdi pozici\n");
	printf("12 - Pridej pohovor\n");
	printf("13 - Edituj stav pohovoru\n");
	printf("14 - Vypis pohovory\n");
	printf("15 - Ukonceni programu\n");
	printf("Zadejte cislo\n");
}
void pridaniKandidata() {
	int id = 0;
	char jmeno[50];
	enum OBOR obor = 0;
	char tel[50];
	char email[50];
	char jazyky[100];


	printf("Zadejte ID\n");
	scanf("%d", &id);

	printf("Zadejte jmeno\n");
	scanf("%s", jmeno);

	printf("Zadejte obor\n");
	scanf("%d", &obor);

	printf("Zadejte telefon\n");
	scanf("%s", tel);

	printf("Zadejte email\n");
	scanf("%s", email);

	printf("Zadejte jazyky\n");
	scanf("%s", jazyky);

	stKandidat* novyKandidat = vytvorKandidata(id, jmeno, obor, tel, email, jazyky);
	pridejKandidata(novyKandidat);
	printf("Kandidat byl pridan\n");
}
void pridaniPozice() {
	int id = 0;
	char pozice[100];
	enum KRAJ kraj = 0;
	char popis[50];
	char pozadavky[50];
	char nabidka[50];
	char jazyky[50];
	float plat = 0;


	printf("Zadejte ID\n");
	scanf("%d", &id);

	printf("Zadejte pozice\n");
	scanf("%s", pozice);

	printf("Zadejte kraj\n");
	scanf("%d", &kraj);

	printf("Zadejte popis\n");
	scanf("%s", popis);

	printf("Zadejte pozadavky\n");
	scanf("%s", pozadavky);

	printf("Zadejte nabidka\n");
	scanf("%s", nabidka);

	printf("Zadejte jazyky\n");
	scanf("%s", jazyky);

	printf("Zadejte Max Plat\n");
	scanf("%f", &plat);

	stPozice* novaPozice = vytvorPozici(id, pozice, kraj, popis, pozadavky, nabidka, jazyky, plat);
	pridejPozice(novaPozice);
	printf("Pozice byla pridana\n");
}
void odeberKandidata() {
	int id = 0;
	printf("Zadejte ID kandidata, ktereho chcete odebrat\n");
	scanf("%d", &id);
	stKandidat* odebranyKandidat = odeberKandidataZeSeznamu(id);
	if (odebranyKandidat != NULL) {
		printf("Kandidat %s byl odebran\n", odebranyKandidat->jmeno);
		free(odebranyKandidat);	
	}
	
}
void odeberPozici() {
	int id = 0;
	printf("Zadejte ID pozice, kterou chcete odebrat\n");
	scanf("%d", &id);
	stPozice* odebranaPozice = odeberPoziciZeSeznamu(id);
	if (odebranaPozice != NULL) {
		printf("Pozice %s byla odebrana\n", odebranaPozice->pozice);
		free(odebranaPozice);
	}
}
void najdiKandidata() {
	int id = 0;
	printf("Zadejte ID hledaneho kandidata\n");
	scanf("%d", &id);
	stKandidat* hledanyKandidat = najdiKandidataZeSeznamu(id);
	if (hledanyKandidat != NULL) {
		printf("Hledany kandidat: jmeno: %s\n", hledanyKandidat->jmeno);
	}
}
void najdiPozici() {
	int id = 0;
	printf("Zadejte ID hledane pozice\n");
	scanf("%d", &id);
	stPozice* hledanaPozice = najdiPoziceZeSeznamu(id);
	if (hledanaPozice != NULL) {
		printf("Hledana pozice: nazev: %s\n", hledanaPozice->pozice);
	}
}
void pridejNovyPohovor() {
	int idKandidata = 0;
	printf("Zadejte ID hledaneho kandidata\n");
	scanf("%d", &idKandidata);
	stKandidat* hledanyKandidat = najdiKandidataZeSeznamu(idKandidata);


	int idPozice = 0;
	printf("Zadejte ID hledane pozice\n");
	scanf("%d", &idPozice);
	stPozice* hledanaPozice = najdiPoziceZeSeznamu(idPozice);

	
		stPohovor* novyPohovor = vytvorPohovor(hledanyKandidat, hledanaPozice);
		pridejPohovor(novyPohovor);
		printf("Pohovor byl vytvoren\n");
	

}
void vypisObsahuEditace() {
	printf("1 - Nenastaveno\n");
	printf("2 - Nastaveno\n");
	printf("3 - Zaslano CV\n");
	printf("4 - Prijat\n");
	printf("5 - Neprijat\n");
	printf("6 - Odmitl\n");
	printf("7 - Pozastaven\n");
	printf("Zadejte hodnotu\n");
}
void editacePohovoru() {
	int idPohovoru = 0;
	printf("Zadejte ID pohovoru ktere chcete editovat\n");
	scanf("%d", &idPohovoru);

	vypisObsahuEditace();
	int hodnotaEnumu = 0;
	scanf("%d", &hodnotaEnumu);

	enum STAV_POHOVORU stav = hodnotaEnumu;

	if (stav != NULL) {
		zmenStavPohovoru(idPohovoru, stav);
		printf("Pohovor byl zmenen\n");
	}
	else {
		printf("Nastala chyba\n");
	}

}

int main(void) {

	char* souborKandidati = "kandidati.csv";
	char* souborPozice = "pozice.csv";
	int opakuj = 1;
	int prikaz = 0 ;
	alokujPolePohovoru();

	do
	{
		vypisObsahu();
		scanf("%d", &prikaz);

		switch (prikaz) {

		case 1:
			nactiSeznamKandidat(souborKandidati);
			printf("Seznam kandidatu byl nacten\n");
			break;
		case 2:
			nactiSeznamPozic(souborPozice);
			printf("Seznam pozic byl nacten\n");
			break;
		case 3:
			vypisSeznamKandidatu();
			break;
		case 4:
			vypisSeznamPozic();
			break;
		case 5:
			pridaniKandidata();
			break;
		case 6:
			pridaniPozice();
			break;
		case 7:
			odeberKandidata();
			break;
		case 8:
			odeberPozici();
			break;
		case 9:
			zrusSeznamKandidatu();
			zrusSeznamPozic();
			printf("seznamy byly zrušeny\n");
			break;
		case 10:
			najdiKandidata();
			break;
		case 11:
			najdiPozici();
			break;
		case 12:
			pridejNovyPohovor();
			break;
		case 13:
			editacePohovoru();
			break;
		case 14:
			vypisPohovory();
			break;
		case 15:
			opakuj = 0;
			break;
		}

	} while (opakuj);
	
	dealokacePolePohovory();
	zrusSeznamKandidatu();
	zrusSeznamPozic();


	_CrtDumpMemoryLeaks();
	return 0;
}
