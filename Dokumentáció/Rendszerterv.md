# Rendszerterv – Receptkezelő alkalmazás (JavaFX)

## 1. A rendszer célja
Az alkalmazás célja egy egyszerűen kezelhető, felhasználóbarát felület biztosítása, amely segítséget nyújt a felhasználók számára receptek rögzítéséhez, rendszerezéséhez, szerkesztéséhez és kereséséhez. A rendszer lehetőséget ad a recept hozzávalóinak, leírásának, elkészítési idejének és kategóriájának rögzítésére. Az alkalmazás célja a főzési élmény megkönnyítése, egyéni gyűjtemények létrehozása és a receptek gyors elérése.

## 2. Projektterv

### 2.1 Projektszerepkörök, felelőségek
- **Üzleti szereplő**
  - Megrendelő: Konyhai Segéd Bt.

### 2.2 Projektmunkások és felelőségek
- Frontend és backend: Győrfi Bence, Sálek Dániel  
- Tesztelés: Győrfi Bence, Sálek Dániel
  
### 2.3 Ütemterv

| Funkció         | Feladat                        | Prioritás | Becslés (nap) | Aktuális becslés | Eltelt idő | Becsült idő |
|------------------|-------------------------------|-----------|----------------|-------------------|-------------|--------------|
| Rendszerterv     | Megírás                       | 1         | 1              | 1                 | 1           | 1            |
| UI-terv          | Menük és nézetek megalkotása  | 2         | 2              | 2                 | 2           | 2            |
| Program          | Prototípus elkészítése        | 3         | 3              | 3                 | 2           | 1            |
| Program          | Tesztelés                     | 4         | 1              | 1                 | 1           | 1            |

### 2.4 Mérföldkövek
- 05.10. Projekt kezdete  
- 05.11. Alap prototípus  
- 05.12. Funkciók befejezése  
- 05.12. Tesztelés  
- 05.13. Bemutatás és átadás

## 3. Üzleti folyamatok modellje

### 3.1 Üzleti szereplők
A receptkezelő alkalmazás szabadon használható regisztráció nélkül. A felhasználók helyi adatbázisban tárolhatják saját receptjeiket.

### 3.2 Üzleti folyamatok
- Recept hozzáadása: név, hozzávalók, leírás, idő, kategória
- Recept módosítása
- Recept törlése
- Recept keresése név vagy hozzávaló alapján
- Receptlista megjelenítése, szűrés kategória szerint

## 4. Követelmények

### 4.1 Funkcionális követelmények

| ID | Megnevezés               | Leírás                                                                                      |
|----|--------------------------|---------------------------------------------------------------------------------------------|
| K1 | Recept hozzáadása        | A felhasználó új receptet adhat hozzá a saját gyűjteményéhez.                              |
| K2 | Recept szerkesztése      | A felhasználó módosíthatja a korábban elmentett recepteket.                                 |
| K3 | Recept törlése           | A felhasználó törölheti a korábban elmentett recepteket.                                    |
| K4 | Recept keresés           | A felhasználó név vagy hozzávaló alapján kereshet.                                          |
| K5 | Receptlista megtekintése | A felhasználó kilistázhatja az összes receptet, szűrhet kategória szerint.                  |

### 4.2 Nemfunkcionális követelmények

| ID | Megnevezés                          | Leírás                                                                                   |
|----|-------------------------------------|------------------------------------------------------------------------------------------|
| K6 | Átlátható, intuitív felület         | A kezelőfelület legyen letisztult és könnyen értelmezhető minden korosztály számára.     |
| K7 | Adatkezelés biztonsága              | A receptadatokat lokálisan, szerializálással vagy adatbázissal biztonságosan tárolja.    |
| K8 | Tervezési minták használata         | Az alkalmazás tartalmaz legalább két tervezési mintát (pl. MVC, Singleton).              |

Támogatott eszközök:
- JavaFX futtatására képes platformok: Windows, Linux, macOS (elsődlegesen Windows)

## 5. Funkcionális terv

### 5.1 Rendszerszereplők
- Felhasználó:
  - Receptek létrehozása, szerkesztése, törlése, keresése
- Rendszer:
  - Tárolás, visszakeresés, frissítés, rendezés

### 5.2 Menühierarchia
- Főmenü
  - Recept hozzáadása
  - Receptek listázása
    - Recept szerkesztése
    - Recept törlése
    - Recept megtekintése
  - Keresés
  - Beállítások (pl. világos/sötét mód)


## 6. Fizikai környezet

### Vásárolt szoftverkomponensek, valamint esetleges külső rendszerek
Nincsenek vásárolt szoftverkomponensek.
### Hardver topológia
Olyan számítógép alkalmas, amely Windows 10 vagy 11 operációs rendszerrel rendelkezik.
### Fizikai alrendszerek
Kliens gépek: A követelményeknek megfelelő, Windows 10 vagy 11 operációs rendszerrel rendelkező PC-k.
Szerver (Host) gép: Az adatbázis rendszer és a háttérfolyamatokat ellátó szolgáltatáshoz szükséges összetevők itt találhatóak. A kliens gép ezzel kommunikál.
### Fejlesztő eszközök
 - IntelliJ IDEA

## 7. Architekturális terv

### Webszerver

### Adatbázis rendszer

### A program elérése, kezelése

## 8. Adatbázis terv

## 9. Implementációs terv
A projektet két részre oszlik: a frontendre és a backendre. A frontend Java Swing segítségével készül, míg a backend Java keretrendszerben.
A program kódjában használt nyelv elsősorban az angol.
A szerver és az adatbázist összekötő csomag  felelős az adatbázis kezeléséért.
Mind a frontend, mind a backend fejlesztéséhez szükség van a IntelliJ környezet telepítésére és konfigurálására a szükséges eszközökkel és kiegészítőkkel
együtt.
## 10. Tesztterv

A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata,
ellenőrzése a rendszer által megvalósított üzleti szolgáltatások verifikálása.
A teszteléseket a fejlesztői csapat minden tagja elvégzi.
Egy teszt eredményeit a tagok dokumentálják külön unit teszt függvényekbe és metódusokba tárolja.

A tesztelés során a szoftver megfelelő működését vizsgáljuk. Amennyiben az elvártnak megfelelő eredményt kapunk, a teszt eset sikeresnek tekinthető, ellenkező esetben a hibát megpróbáljuk elhárítani, ha a teszt nem direkt nem sikerül.

### Tesztesetek

#### Tesztelés módja: Unit Teszt

 | Teszteset      | Elvárt eredmény                                                                                            | 
 |----------------|------------------------------------------------------------------------------------------------------------| 

## 11. Telepítési terv

**Fizikai telepítési terv**:

**Szoftver telepítési terv**:
 - A felhasználónak szüksége van egy Windows 10 vagy 11 operációs rendszerre, amely támogatja a Java alkalmazásokat.
 - A szoftverünk futtatható Windows szerveren.
 - Szükség van valamilyen adatbázis szerverre, például MySQL:
   - Szükséges telepíteni az XAMPP nevű szoftvert.
   - Az adatbázis konfigurálása az XAMPP segítségével történik.
 - A backend és frontend konfigurálásához szükség van az  IntelliJ fejlesztői környezetre.
   -  Szükséges csomagok a Java.
 - A fejlesztők számára az alkalmazás szabadon konfigurálható, fejleszthető.
 - Abban az esetben, ha a szükséges beállítások megtörténtek, a felhasználók számára az alkalmazás futtatható

## 12. Karbantartási terv
Fontos ellenőrizni:
*	Az alkalmazás megfelelően kezeli a kritikus információkat, azok nem elérhetők a megfelelő jogkör és felhasználói adatok nélkül. Ilyenek például a bejelentkezési adatok, és a felhasználók személyes adatai adatai.

Figyelembe kell venni a felhasználó által jött visszajelzést is a programmal kapcsolatban.
Ha hibát talált, mielőbb orvosolni kell, lehet az:
*	Működéssel kapcsolatos
*	Kinézet, dizájnnal kapcsolatos
