# Şifre Kaydedici v2

### Açıklamalar
Şifre kaydedici Java **swing** ve Java **awt** kütüphaneleri kullanılarak yazılan kullanıcı arayüz programıdır.Şifre Kaydedicinin amacı internet bağlantısı olmadan şifrelerinizi güvenilir yere yani programın kurulu olduğu yere **SQLite** kullanarak kayıt eder.
İlk olarak programda kaydımız yok ise kayıt oluyoruz.Kayıt işlemi tamamlandıktan sonra kullanıcı adı ve şifremizi girerek oturumu açıyoruz.Programda 4 ayrı işlev bulunmakta.Bunlar şifre ekle,şifre sil,şifre ara ve şifre güncelle.

`Şifre Kaydedici v2 Yenilikler` 
+ *(15.06.17)*
-Giriş panelinde kayıt olma bölümü ve güncelle paneli dialog paneli yeniden tasarlandı.
-Giriş panelinde id ve şifre bölümlerine enter tuşuna basınca giriş fonsiyonu eklendi.
-Giriş panelinde şifre artık gözükmüyor onun yerine '*' sembolü yazılıyor.
-Menü de artık kullanıcı kendi profil resmini koyabiliyor.
-Ara,sil ve güncelle bölümlerindeki tabloların hücreleri artık değiştirilemiyor.
-Tablolardaki veriler artık hücrenin ortasında.
-Ekle bölümünde, artık ekle sembolüne tıklamadan enter tuşu ile ekleme yapılabiliyor.
-Ara bölümünde ara sembolüne basmadan artık tablo filtreleniyor.
-Sil bölümünün arka plan resminde güncelleme yapıldı.
-Kodlamada iyileştirmeler yapıldı.

![EKLE](https://github.com/oktaykcr/SifreKaydedici/blob/master/Sifre%20Kaydedici/Resources/EkleButonAnim.png) <br />
`EKLE`
- Ekle butonuna tıklayarak ekrana gelen pencere de *Sifre Adı,Id,Şifre,Email ve Açıklama* girilerek ekle butonuna bastıktan sonra veri tabanına şifremizi kayıt edebiliriz.

![SIL](https://github.com/oktaykcr/SifreKaydedici/blob/master/Sifre%20Kaydedici/Resources/SilButonAnim.png) <br />
`SİL`
- Sil butonuna tıklayarak ekrana gelen tabloda daha önceden kayıt ettiğimiz şifreleri görebilirsiniz.Silmek istediğiniz şifreye tıklayarak ve sonra sil butonuna tıklayarak şifrenizi silebilirsiniz.

![ARA](https://github.com/oktaykcr/SifreKaydedici/blob/master/Sifre%20Kaydedici/Resources/AraButonAnim.png) <br />
`ARA`
- Ara butonuna tıklayarak daha önceden kayıt ettiğiniz bütün şifreleri tabloda görebilirsiniz.Metin alanına bulmak istediğiniz şifre adını girerek tabloyu filtreleyebilirsiniz ve bulmak istediğiniz şifreye kolaylıkla ulaşabilirsiniz.

![GUNCELLE](https://github.com/oktaykcr/SifreKaydedici/blob/master/Sifre%20Kaydedici/Resources/GuncelleButonAnim.png) <br />
`GÜNCELLE`
- Güncelle butonuna tıklayarak ara ve sil panellerinde olduğu gibi karşımıza daha önceden kayıt ettiğimiz tablo gelecek ve güncellemek isteğimiz şifreye tıklayarak,program yeni bir pencere açacak.Program otomatik olarak daha önceki kayıt halini metin alanlarına dolduracak ve siz sadece güncellemek istediğinz yeri değiştireceksiniz.

### Kurulum
`SifreKaydediciV2Setup.exe` dosyasını çalıştırarak programın kurulumunu yapabilirsiniz.

### Emeği Geçenler
- Oktay Koçer

### Lisans
[Lisans Dosyası](LICENSE)




