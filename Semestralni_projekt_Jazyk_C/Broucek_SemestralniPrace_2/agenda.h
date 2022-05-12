#pragma once
#include "kandidat.h"
#include "pozice.h"
#include "pohovor.h"
#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

void nactiSeznamKandidat(char* nazevSouboru);

void nactiSeznamPozic(char* nazevSouboru);

void vypisSeznamKandidatu();

void pridejKandidata(stKandidat* kandidat);

stKandidat *odeberKandidataZeSeznamu(int cisloKandidata);

stKandidat* najdiKandidataZeSeznamu(int cisloKandidata);

void zrusSeznamKandidatu();

void vypisSeznamPozic();

void pridejPozice(stPozice* pozice);

stPozice* odeberPoziciZeSeznamu(int cisloPozice);

stPozice* najdiPoziceZeSeznamu(int cisloPozice);

void zrusSeznamPozic();

void alokujPolePohovoru();

void pridejPohovor(stPohovor* pohovor);

void zmenStavPohovoru(int id, enum STAV_POHOVORU vysledek);

void vypisPohovory();

void dealokacePolePohovory();




