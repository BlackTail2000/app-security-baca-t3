Drop Database If Exists BD_Seguridad_BacaManrique_T3;
Create Database If Not Exists BD_Seguridad_BacaManrique_T3;
Use BD_Seguridad_BacaManrique_T3;

Create Table Usuario(
id Int Primary Key Auto_Increment,
username Varchar(100) Not Null,
password Char(60) Not Null,
email Varchar(300) Not Null,
rol Varchar(25) Not Null,
activo Bit Not Null);

Insert Into Usuario Values
(Null, 'BlackTail2000',
'$2a$10$60tbEF1/rYDsik5WuyU7eeH.QjgKcjDdMf3y5DESXwTwiaOka.r/K', -- Callie&Marie123
'carlosgabrielbacamanrique@hotmail.com', 'ADMIN', True);