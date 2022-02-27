# Loan Approval Application 

Spring boot ile geliştirilmiş bir kredi sorgulama sistemi. Bu uygulama ile bir kullanıcı kredi alıp alamayacağını; 
alabilecek ise çekebileceği maksimum kredi miktarını sorgulayıp öğrenebilmektedir.

## Proje Detayları

Uygulama basit bir form sayfası ile temel kullanıcı bilgilerini almaktadır. Bunlar kimlik numarası, ad, soyad, 
aylık gelir ve telefon numarası bilgilerinden oluşmaktadır. Form aracılığıyla alınan bilgiler maksimum limit sorgulama 
işlemi için ilgili metoda iletilmektedir. Aynı zamanda alınan bilgiler ile kullanıcı profili kullanıcı veritabanına 
kaydedilmektedir. Yapılan sorgulama işlemi sonucunda ortaya çıkan çekilebilir maksimum kredi miktarı, kullanıcının 
telefon numarasına SMS ile gönderilmektedir. (SMS ile gönderme işlemi gerçekten SMS göndermemekte, yalnızca basit bir 
metot ile simüle etmektedir. Oluşturulan SMS mesajı terminalde gösterilmektedir.)

Kredi limiti sorgulama işleminin yanı sıra kullanıcılar daha önceden gerçekleştirilmiş bir kredi limiti sorgulama 
işleminin sonucunu da görüntüleyebilmektedir. Bunun için yalnızca kimlik numarasıyla bir sorgulama yapılmakta ve ilgili 
kimlik numarasına ait maksimum çekilebilir kredi bilgisi görüntülenebilmektedir.

Ayrıca tanımlanmış olan ilgili url uzantıları aracılığıyla kullanıcı veri tabanı üzerinde standart CRUD işlemleri 
yapılabilmektedir. Dolayısıyla veritabanına yeni kullanıcılar tanımlanabilmekte, var olan kullanıcı bilgileri 
güncellenebilmekte, mevcut kullanıcı bilgileri silinebilmekte ve mevcut tüm kullanıcıların bilgileri 
görüntülenebilmektedir.

Önyüz için JavaScript kullanılmıştır.

## Kullanılan Teknolojiler

- Spring Boot
- Hibernate
- Maven
- PostgreSQL
- Swagger
- JavaScript

## Model
- Applicant
- Loan

## Projeyi Çalıştırmak İçin Gerekenler
- Sisteminizde PostgreSQL kurulu olmalıdır
- "application.properties" dosyasında PostgreSQL username ve password alanları kendi bilgilerinizle güncellenmelidir
- Uygulama PostgreSQL üzerinden "5434" portunu kullanmaktadır, bu portun boşta olduğundan emin olunuz
- Swagger adresi: http://localhost:8080/swagger-ui/index.html?configUrl=/loan-approval-app/swagger-config
- 

