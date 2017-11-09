#pragma once
#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <ctime>
#include <iostream>
#include <fstream>
#include <iomanip>

#define WSPOLCZYNNIK_NAUKI  0.1
#define MAX_ITERACJE 20

using namespace std;

class Perceptron
{
	int tab_wej[20][24];  //tablica tab[n][m] gdzie n to ilosc danych uczacych a m to ilosc znakow w kazdej danej
	float tab_wag[24];	//tablica zawierajaca wagi dla kazdego wejscia do perceptronu
	int tab_wyn_ocz[20];	//tablica zawiera oczekiwane wyniki (1 lub 0) dla danych uczacych
	int ilosc_wejsc;	//ilosc wejsc w zaleznosci od podanj ilosci danych wejsciowych
	bool wynik;	//wynik po sprawdzeniu przez funkcje sprawdz(int tab[]) true albo false
	float wspolczynnik_nauki;	//wspolczynnik nauki w funkcji ucz()

public:
	Perceptron();	//konstruktor domyslny, zawiera ustawienie danych domyslnych oraz funkcje wczytania danych
	void wczytanie_danych();	//wczytanie danych z pliku
	void losuj_wagi();	// losowanie wag z przedzia³u od 0 do 1
	float licz_wyjscia(int i); //funkcja sumujšca perceptronu
	void Widrow_Hoff();	//funkcja uczaca na podstawie roznicy wyniku tymczasowego i wyniku oczekiwanego
	void sprawdz(int tab[]);	//funkcja sprawdzajaca po zakonczeniu procesu uczenia
	void wypisz();	//wypisanie wyniku sprawdzania
	~Perceptron();	//destruktor domyslny
};