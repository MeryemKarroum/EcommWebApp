INSERT INTO product (id, category_id, description, name, price)
VALUES
(1, 1, 'soin de visage hydratant très efficace', 'Vichy-Hydra', 200),
(2, 1, 'Crème anti-âge et raffermissante pour le visage', 'L\'Oréal Revitalift', 250),
(3, 1, 'Sérum éclaircissant et anti-taches', 'Estée Lauder Advanced Night Repair', 350),
(4, 1, 'Masque hydratant pour peau sèche', 'Clinique Moisture Surge', 180),
(5, 1, 'Crème de jour nourrissante et apaisante', 'Neutrogena Hydro Boost', 170),
(6, 2, 'Gel douche nourrissant au parfum de vanille', 'Dove Softening', 100),
(7, 2, 'Crème corporelle hydratante pour peaux sèches', 'Nivea Soft', 120),
(8, 2, 'Huile sèche nourrissante et satinée', 'Bio-Oil', 160),
(9, 2, 'Exfoliant au sucre pour le corps', 'The Body Shop Shea Body Scrub', 220),
(10, 3, 'Shampooing réparateur pour cheveux secs et abîmés', 'L\'Oréal Elvive Dream Lengths', 150),
(11, 2, 'Gel douche nourrissant au parfum de noix de coco', 'Hemalaya-Baindouche', 100),
(12, 3, 'Masque capillaire nourrissant et réparateur', 'Olaplex No. 3', 300),
(13, 3, 'Sérum anti-frisottis pour cheveux lisses', 'John Frieda Frizz Ease', 220),
(14, 4, 'Crème nourrissante pour les mains', 'Neutrogena Norwegian Formula', 90),
(15, 4, 'Crème anti-âge pour les mains', 'L\'Occitane en Provence', 180),
(16, 5, 'Fond de teint matifiant longue tenue', 'Maybelline Fit Me', 150),
(17, 5, 'Mascara volumateur et allongeant', 'Lancôme Hypnôse', 250),
(18, 5, 'Rouge à lèvres mat longue durée', 'MAC Ruby Woo', 220),
(19, 5, 'Poudre libre fixante et matifiante', 'Laura Mercier Translucent Powder', 280),
(20, 5, 'Palettes d\'ombres à paupières avec tons neutres', 'Urban Decay Naked Palette', 450);



INSERT INTO category (id, name) VALUES
(1, 'soin visage'),
(2, 'soin corps'),
(3, 'soin cheveux'),
(4, 'soin mains'),
(5, 'maquillage');
