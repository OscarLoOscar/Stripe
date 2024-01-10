use shopping;
select * from product;

select * from user;

select * from cart_item;
TRUNCATE TABLE  cart_item;

select * from transaction;
TRUNCATE TABLE  transaction;

ALTER TABLE transaction DROP COLUMN total;

select * from transaction_product;
TRUNCATE TABLE  transaction_product;

select * from cart_item where uid=1;

INSERT INTO product ( image_url, description, name, price, unit) VALUES
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/m/o/mouton_2004.png', 'CH.MOUTON ROTHSCHILD PAUILLAC 2004 750ml', 'CH.MOUTON ROTHSCHILD', 7800.00, 150),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/l/a/lascombes.png', 'CH.LASCOMBES MARGAUX 2019', 'CH.LASCOMBES MARGAUX 2019', 880.00, 200),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/l/a/la_croix_de_beaucaillou_1.png', 'LA CROIX DE BEAUCAILLOU', 'LA CROIX DE BEAUCAILLOU', 690.00, 120),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/c/a/cantemerle.png', 'CH.CANTEMERLE', 'CH.CANTEMERLE', 480.00, 180),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/b/o/bottle-size-18.png', 'CH.D\'ISSAN', 'CH.D\'ISSAN', 400.00, 90),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/010020391_les_griffons_de_pichon_baron_1.png', 'LES GRIFFONS DE PICHON BARON', 'LES GRIFFONS DE PICHON BARON', 530.00, 160),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/s/a/saint_christophe_medoc.png', 'CH.SAINT CHRISTOPHE', 'CH.SAINT CHRISTOPHE', 190.00, 110),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/010040691_cantenac_brown.png', 'CH.CANTENAC BROWN', 'CH.CANTENAC BROWN', 670.00, 140),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/010083461_larcis_ducasse.png', 'CH.LARCIS DUCASSE', 'CH.LARCIS DUCASSE', 950.00, 130),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/l/e/leoville_las_cases_90.png', 'CH.LEOVILLE LAS CASES', 'CH.LEOVILLE LAS CASES', 950.00, 100),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/p/a/pavie_decesse_bouteille.png', 'CH.PAVIE DECESSE', 'CH.PAVIE DECESSE', 3280.00, 150),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/l/a/la_mission_lalande_de_pomerol_1.png', 'CH.LA MISSION', 'CH.LA MISSION', 1600.00, 200),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/010020161_lafite_rothschild_6.png', 'CH.LAFITE ROTHSCHILD', 'CH.LAFITE ROTHSCHILD', 16000.00, 120),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/018a70011_villemaurine.png', 'CH.VILLEMAURINE', 'CH.VILLEMAURINE', 360.00, 180),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/g/i/giscours_1.png', 'CH.GISCOURS', 'CH.GISCOURS', 850.00, 90),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/010020161_lafite_rothschild_3.png', 'CH.LAFITE ROTHSCHILD', 'CH.LAFITE ROTHSCHILD', 16000.00, 160),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/l/a/la_conseillante_1.png', 'CH.LA CONSEILLANTE', 'CH.LA CONSEILLANTE', 2600.00, 110),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/0/1/010030491_branaire_ducru_1_2.png', 'CH.BRANAIRE DUCRU', 'CH.BRANAIRE DUCRU', 720.00, 140),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/c/h/chevalier_de_lascombes.png', 'CHEVALIER DE LASCOMBES', 'CHEVALIER DE LASCOMBES', 460.00, 130),
( 'https://eshop.enoteca.com.hk/media/catalog/product/cache/d511bb89dcbffe0443e0c0ed3fc78e32/b/o/bottle-size-16.png', 'CH.GISCOURS MARGAUX 2016', 'CH.GISCOURS MARGAUX 2016', 570.00, 10);


