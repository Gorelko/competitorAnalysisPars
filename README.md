# competitorAnalysisPars
Данная программа преднозначена для анализа рынка автозапчастей (цены/ассортимент/наличие).

ВАЖНО! В проекте я использую jdk1.8, поэтому, если настроить переменную среды на более свежую версию, велика вероятность, что spring при загрузке выкинет ошибку.

Порядок установки/настройки программы:
0.	Скачайте и установите JAVA (если не установлена): https://java.com/ru/download/

1.	Скачиваем браузер FIREFOX по ссылке: https://www.mozilla.org/ru/firefox/download/thanks/

2.	После установки FIREFOX, 
-	Нажмите   +R на клавиатуре. Откроется диалоговое окно Выполнить.
-	В диалоговом окне Выполнить наберите:
firefox.exe -P
 -	Жмём ОК
-	Удаляем все профили:
-	После, жмём “Создать” и “Далее”
-	Создаем пользователя “profileTools” и жмём “Готово”
 
3.	Зайдите на официальный сайт мавен в раздел загрузка (http://maven.apache.org/download.cgi) и скачайте последнюю стабильную версию.
Распакуйте архив в инсталляционную директорию. Например в C:\Program Files\maven\
В Windows кликните правой кнопкой мыши на "мой компьютер" ->свойства->дополнительные параметры->переменные среды->системные переменные и там добавьте "M2_HOME" и "C:\Program Files\maven\"
Везде нажимайте OK
Нажмите   +R на клавиатуре и наберите “cmd”, после чего нажмите ENTER. 

Проверьте корректность установки, набрав в командной строке
mvn –version

Если результат примерно такой
    dima@myhost ~ $ mvn -version
    Apache Maven 3.0 (r1004208; 2010-10-04 15:50:56+0400)
    Java version: 1.6.0_22
    Java home: /opt/sun-jdk-1.6.0.22/jre
    Default locale: ru_RU, platform encoding: UTF-8
    OS name: "linux" version: "2.6.34-gentoo-r12" arch: "amd64" Family: "unix"
 то поздравляю, вы успешно установили Maven.
 
4.	Указываем в настройках путь к JAVA (переменная среды как указывается в настройках наглядно в видео): https://www.youtube.com/watch?v=EEqScHr_bec
Либо, пример установки можно посмотреть по ссылке https://techarks.ru/general/kak-ustanovit-java_home-v-windows-10/)

5. Создайте папку (например D:\parsing) и поместите туда программу.

6. Создайте папку D:\parsing\parsing\geckodriver.
Скачайте geckodriver.exe для своей операционной системы: https://github.com/mozilla/geckodriver/releases
После geckodriver.exe поместите в папку -> D:\parsing\parsing\geckodriver\geckodriver.exe

7. В папке D:\parsing создайте две дополнительные папки: uploads и ready (куда будут складываться подкачаные файлы и результат сбора информации).
Должно получиться: D:\parsing\uploads и D:\parsing\ready

8. Программу можно запускать через start.bat находящийся в папке с программой mywebparsingeasy\start.bat (при условии, что все предыдущие пункты выполнены).

9. Для запуска в браузере набрать http://localhost:8181/

10. Перед тем как запустить парсер необходимо заренее выбрать файл с интересующей продукцией (в ходе чего будет спаршена вся информация по аналогам). Пример файла в формате CSV по ссылке: https://disk.yandex.by/d/fNgc_Qw_kH3urg

11. Полученный результат также выгружается в формате CSV в заранее указанную вами папку. Открывать данный файл с учётом кодировки (на разных компьюторах по умолчанию может стоять разные кодировки (обычно это UTF-8 или windows-1251)).

Если возникли вопросы, пишите в телеграм: @Uladzi0991