LOCK TABLES poc_legacy.patients WRITE;

INSERT INTO poc_legacy.patients
  (id,    title,  first_name, last_name,      address_1,    address_2,          address_3,    postcode, phone,              date_of_birth, gender,    nhs_number,  pas_number,   department_id, gp_id)
VALUE
  (1,     'Mrs',  'Marian',   'Walsh',        '51',         'Douglas Road',     'Dublin',     'D8',     '(011981) 32362',   '1944-06-06',  'Female',  9999999000,  352541,       1,             1),
  (2,     'Ms',   'Rachel',   'O''Brien',     '75',         'High Street',      'Dublin',     'D8',     '(0112) 740 5408',  '1920-08-08',  'Female',  9999999001,  623454,       1,             2),
  (3,     'Ms',   'Ciara',    'Walsh',        '60',         'SeaView Road',     'Dublin',     'D8',     '0800 1111',        '1937-09-28',  'Female',  9999999002,  346574,       2,             3),
  (4,     'Ms',   'Emma',     'Smith',        '40',         'High Street',      'Dublin',     'D8',     '07624 647524',     '1980-02-03',  'Female',  9999999003,  332546,       1,             4),
  (5,     'Ms',   'Ciara',    'Smith',        '88',         'SeaView Road',     'Dublin',     'D8',     '0964 934 2028',    '1958-05-14',  'Female',  9999999004,  345267,       3,             5),
  (6,     'Mrs',  'Fiona',    'Murphy',       '19',         'Douglas Road',     'Dublin',     'D8',     '0909 764 2554',    '1952-02-19',  'Female',  9999999005,  798311,       3,             6),
  (7,     'Ms',   'Ciara',    'Walsh',        '64',         'High Hill',        'Dublin',     'D8',     '070 6691 5178',    '1958-06-24',  'Female',  9999999006,  595941,       4,             7),
  (8,     'Ms',   'Rachel',   'Walsh',        '45',         'SandyMill Road',   'Dublin',     'D8',     '(01141) 56013',    '1969-01-31',  'Female',  9999999007,  841652,       4,             8),
  (9,     'Ms',   'Emma',     'O''Brien',     '53',         'Talbot Road',      'Dublin',     'D8',     '(026) 5155 5992',  '1938-06-26',  'Female',  9999999008,  685412,       4,             9),
  (10,    'Mrs',  'Nora',     'Kelly',        '80',         'Low Street',       'Dublin',     'D8',     '(016977) 0472',    '1981-06-05',  'Female',  9999999009,  584849,       4,             10),
  (11,    'Ms',   'Nora',     'Smith',        '40',         'High Hill',        'Dublin',     'D8',     '(01970) 784490',   '1971-06-25',  'Female',  9999999010,  556631,       1,             11),
  (12,    'Mrs',  'Ciara',    'Kelly',        '31',         'High Hill',        'Dublin',     'D8',     '(0101) 279 8943',  '1986-12-10',  'Female',  9999999011,  645154,       1,             12),
  (13,    'Mrs',  'Emma',     'O''Brien',     '96',         'SeaView Road',     'Dublin',     'D8',     '076 5024 5403',    '1946-10-04',  'Female',  9999999012,  984313,       2,             13),
  (14,    'Ms',   'Fiona',    'Kelly',        '19',         'Summer Hill',      'Dublin',     'D8',     '0800 252 0881',    '1973-03-14',  'Female',  9999999013,  416166,       3,             14),
  (15,    'Ms',   'Rachel',   'O''Sullivan',  '55',         'High Hill',        'Dublin',     'D8',     '056 5661 0225',    '1949-10-12',  'Female',  9999999014,  641515,       3,             15),
  (16,    'Ms',   'Zoe',      'Murphy',       '98',         'Douglas Road',     'Dublin',     'D8',     '0353 893 7620',    '1992-11-08',  'Female',  9999999015,  949461,       3,             16),
  (17,    'Mrs',  'Ciara',    'O''Brien',     '26',         'SeaView Road',     'Dublin',     'D8',     '(025) 5608 1829',  '1975-12-27',  'Female',  9999999016,  994512,       4,             17),
  (18,    'Ms',   'Marian',   'Kelly',        '91',         'Douglas Road',     'Dublin',     'D8',     '(0113) 310 6232',  '1970-07-14',  'Female',  9999999017,  684135,       4,             18),
  (19,    'Ms',   'Zoe',      'Murphy',       '94',         'High Street',      'Dublin',     'D8',     '0800 970 8555',    '1984-07-06',  'Female',  9999999018,  943513,       4,             19),
  (20,    'Mrs',  'Rachel',   'Walsh',        '34',         'Summer Hill',      'Dublin',     'D8',     '(01326) 24257',    '1944-12-19',  'Female',  9999999019,  684351,       4,             20),
  (21,    'Mrs',  'Ciara',    'Kelly',        '49',         'High Street',      'Dublin',     'D8',     '0800 1111',        '1986-08-01',  'Female',  9999999020,  913513,       1,             21),
  (22,    'Ms',   'Nora',     'Murphy',       '78',         'Low Street',       'Dublin',     'D8',     '0800 320 3878',    '1983-06-12',  'Female',  9999999021,  984511,       1,             22),
  (23,    'Mrs',  'Marian',   'Walsh',        '24',         'Low Street',       'Dublin',     'D8',     '0819 258 9104',    '1990-01-11',  'Female',  9999999022,  986413,       2,             23),
  (24,    'Ms',   'Jill',     'Smith',        '40',         'SandyMill Road',   'Dublin',     'D8',     '0845 46 42',       '1962-05-13',  'Female',  9999999023,  651144,       3,             24),
  (25,    'Ms',   'Rachel',   'O''Sullivan',  '36',         'High Street',      'Dublin',     'D8',     '(016977) 1616',    '1938-02-19',  'Female',  9999999024,  984354,       3,             25),
  (26,    'Ms',   'Mary',     'Smith',        '2',          'Summer Hill',      'Dublin',     'D8',     '0800 517 2480',    '1988-12-30',  'Female',  9999999025,  643133,       3,             26),
  (27,    'Mrs',  'Ciara',    'O''Brien',     '20',         'Talbot Road',      'Dublin',     'D8',     '0800 1111',        '1979-10-16',  'Female',  9999999026,  843513,       4,             27),
  (28,    'Ms',   'Marian',   'O''Brien',     '72',         'Summer Hill',      'Dublin',     'D8',     '0800 1111',        '1992-04-13',  'Female',  9999999027,  894351,       4,             28),
  (29,    'Ms',   'Emma',     'O''Brien',     '39',         'Douglas Road',     'Dublin',     'D8',     '0845 46 41',       '1948-09-08',  'Female',  9999999028,  986413,       4,             29),
  (30,    'Mrs',  'Jill',     'O''Brien',     '45',         'Talbot Road',      'Dublin',     'D8',     '0845 46 45',       '1960-02-05',  'Female',  9999999029,  213513,       4,             30),
  (31,    'Ms',   'Mary',     'Kelly',        '45',         'SeaView Road',     'Dublin',     'D8',     '(01375) 543488',   '1948-12-05',  'Female',  9999999030,  984313,       1,             31),
  (32,    'Ms',   'Emma',     'Smith',        '98',         'Low Street',       'Dublin',     'D8',     '0800 1111',        '1961-02-04',  'Female',  9999999031,  897454,       1,             32),
  (33,    'Ms',   'Marian',   'Walsh',        '73',         'Mallow View',      'Dublin',     'D8',     '(013047) 21772',   '1944-02-20',  'Female',  9999999032,  987684,       2,             33),
  (34,    'Ms',   'Zoe',      'O''Brien',     '89',         'High Hill',        'Dublin',     'D8',     '(016977) 0130',    '1965-10-16',  'Female',  9999999033,  987677,       3,             34),
  (35,    'Mrs',  'Maria',    'McGowan',      '46',         'SandyMill Road',   'Dublin',     'D8',     '(01774) 13491',    '1933-05-29',  'Female',  9999999034,  955888,       3,             35),
  (36,    'Ms',   'Jill',     'McGowan',      '2',          'SeaView Road',     'Dublin',     'D8',     '0800 1111',        '1980-12-05',  'Female',  9999999035,  561538,       3,             36),
  (37,    'Mrs',  'Jill',     'Smith',        '86',         'High Street',      'Dublin',     'D8',     '(020) 1287 4390',  '1933-10-20',  'Female',  9999999036,  684337,       4,             37),
  (38,    'Ms',   'Jill',     'O''Brien',     '93',         'SandyMill Road',   'Dublin',     'D8',     '(0112) 758 9967',  '1948-03-05',  'Female',  9999999037,  845412,       4,             38),
  (39,    'Mrs',  'Jill',     'O''Brien',     '35',         'Douglas Road',     'Dublin',     'D8',     '056 6233 3741',    '1976-08-17',  'Female',  9999999038,  815182,       4,             39),
  (40,    'Mrs',  'Fiona',    'Murphy',       '87',         'Low Street',       'Dublin',     'D8',     '076 9753 9230',    '1928-12-14',  'Female',  9999999039,  845216,       4,             40),
  (41,    'Mrs',  'Maria',    'Kelly',        '48',         'Douglas Road',     'Dublin',     'D8',     '0800 1111',        '1968-03-14',  'Female',  9999999040,  396641,       5,             41),
  (42,    'Mrs',  'Rachel',   'Smith',        '45',         'Douglas Road',     'Dublin',     'D8',     '0500 070749',      '1974-09-18',  'Female',  9999999041,  843311,       1,             42),
  (43,    'Mrs',  'Jill',     'O''Brien',     '52',         'Talbot Road',      'Dublin',     'D8',     '0845 46 49',       '1982-06-18',  'Female',  9999999042,  845141,       2,             43),
  (44,    'Ms',   'Jill',     'McGowan',      '56',         'SandyMill Road',   'Dublin',     'D8',     '(016977) 1873',    '1974-02-20',  'Female',  9999999043,  843135,       3,             44),
  (45,    'Mrs',  'Fiona',    'O''Connor',    '89',         'High Street',      'Dublin',     'D8',     '(016977) 9167',    '1945-03-15',  'Female',  9999999044,  984331,       3,             45),
  (46,    'Ms',   'Ciara',    'O''Sullivan',  '13',         'Mallow View',      'Dublin',     'D8',     '07662 731734',     '1966-11-27',  'Female',  9999999045,  986431,       3,             46),
  (47,    'Ms',   'Fiona',    'O''Brien',     '95',         'SeaView Road',     'Dublin',     'D8',     '07304 240742',     '1953-09-14',  'Female',  9999999046,  345484,       4,             47),
  (48,    'Ms',   'Zoe',      'O''Brien',     '20',         'SeaView Road',     'Dublin',     'D8',     '0334 839 2867',    '1922-11-15',  'Female',  9999999047,  329974,       4,             48),
  (49,    'Mrs',  'Nora',     'O''Sullivan',  '73',         'High Street',      'Dublin',     'D8',     '0800 803158',      '1992-09-05',  'Female',  9999999048,  641314,       4,             49),
  (50,    'Ms',   'Nora',     'Murphy',       '99',         'SeaView Road',     'Dublin',     'D8',     '055 8108 4579',    '1927-07-03',  'Female',  9999999049,  684318,       4,             50),
  (51,    'Mr',   'Tim',      'O''Connor',    '21',         'Summer Hill',      'Cork',       'CK',     '0845 46 47',       '1974-01-25',  'Male',    9999999050,  343941,       5,             51),
  (52,    'Mr',   'James',    'Smith',        '2',          'Talbot Road',      'Cork',       'CK',     '0800 1111',        '1946-01-14',  'Male',    9999999051,  841531,       1,             52),
  (53,    'Mr',   'John',     'Murphy',       '38',         'Douglas Road',     'Cork',       'CK',     '(01768) 59771',    '1925-03-11',  'Male',    9999999052,  686413,       2,             53),
  (54,    'Mr',   'Tim',      'Smith',        '39',         'Mallow View',      'Cork',       'CK',     '0500 717497',      '1948-04-05',  'Male',    9999999053,  843511,       3,             54),
  (55,    'Mr',   'Tim',      'McGowan',      '27',         'Talbot Road',      'Cork',       'CK',     '0321 837 1197',    '1957-11-14',  'Male',    9999999054,  684138,       3,             55),
  (56,    'Mr',   'Jack',     'O''Sullivan',  '96',         'High Street',      'Cork',       'CK',     '0845 46 47',       '1947-04-22',  'Male',    9999999055,  684311,       3,             56),
  (57,    'Mr',   'Luke',     'O''Sullivan',  '19',         'Douglas Road',     'Cork',       'CK',     '(019194) 78089',   '1973-07-31',  'Male',    9999999056,  941344,       4,             57),
  (58,    'Mr',   'Tim',      'Walsh',        '84',         'High Street',      'Cork',       'CK',     '(016516) 39348',   '1952-03-27',  'Male',    9999999057,  864613,       4,             58),
  (59,    'Mr',   'Bill',     'Walsh',        '57',         'Mallow View',      'Cork',       'CK',     '076 5167 2508',    '1955-05-30',  'Male',    9999999058,  846513,       4,             59),
  (60,    'Mr',   'John',     'O''Brien',     '71',         'High Street',      'Cork',       'CK',     '(0115) 011 9130',  '1922-12-29',  'Male',    9999999059,  313515,       4,             60),
  (61,    'Mr',   'Tim',      'O''Brien',     '73',         'Low Street',       'Cork',       'CK',     '07624 328862',     '1928-03-17',  'Male',    9999999060,  941347,       5,             61),
  (62,    'Mr',   'Jim',      'O''Brien',     '57',         'Talbot Road',      'Cork',       'CK',     '(01331) 241623',   '1947-01-27',  'Male',    9999999061,  897431,       1,             62),
  (63,    'Mr',   'Enda',     'O''Sullivan',  '13',         'Douglas Road',     'Cork',       'CK',     '(01534) 156770',   '1994-07-30',  'Male',    9999999062,  865115,       2,             63),
  (64,    'Mr',   'Tim',      'Smith',        '34',         'SeaView Road',     'Cork',       'CK',     '0895 189 1046',    '1969-11-18',  'Male',    9999999063,  513154,       3,             64),
  (65,    'Mr',   'James',    'O''Connor',    '58',         'SeaView Road',     'Cork',       'CK',     '07755 838533',     '1954-10-17',  'Male',    9999999064,  684154,       3,             65),
  (66,    'Mr',   'James',    'Smith',        '66',         'Low Street',       'Cork',       'CK',     '(016977) 7133',    '1947-01-26',  'Male',    9999999065,  584134,       3,             66),
  (67,    'Mr',   'John',     'Walsh',        '50',         'High Hill',        'Cork',       'CK',     '056 3920 1956',    '1989-11-02',  'Male',    9999999066,  651598,       4,             67),
  (68,    'Mr',   'Jim',      'Murphy',       '28',         'Low Street',       'Cork',       'CK',     '0800 669413',      '1935-08-13',  'Male',    9999999067,  318494,       4,             68),
  (69,    'Mr',   'Luke',     'Smith',        '76',         'Summer Hill',      'Cork',       'CK',     '(01145) 24301',    '1976-09-07',  'Male',    9999999068,  864318,       4,             69),
  (70,    'Mr',   'Luke',     'O''Sullivan',  '22',         'Douglas Road',     'Cork',       'CK',     '(01852) 01318',    '1989-01-02',  'Male',    9999999069,  651545,       4,             70),
  (71,    'Mr',   'Bill',     'Kelly',        '48',         'SeaView Road',     'Cork',       'CK',     '07376 394887',     '1998-02-15',  'Male',    9999999070,  684315,       5,             71),
  (72,    'Mr',   'Jack',     'McGowan',      '2',          'Summer Hill',      'Galway',     'GW',     '(020) 1301 9581',  '1958-07-03',  'Male',    9999999071,  896451,       1,             72),
  (73,    'Mr',   'Michael',  'O''Sullivan',  '58',         'High Street',      'Galway',     'GW',     '076 2031 1395',    '1956-10-17',  'Male',    9999999072,  986413,       2,             73),
  (74,    'Mr',   'John',     'O''Connor',    '12',         'High Hill',        'Galway',     'GW',     '055 0916 7547',    '1977-09-12',  'Male',    9999999073,  684354,       3,             74),
  (75,    'Mr',   'Bill',     'O''Sullivan',  '42',         'Talbot Road',      'Galway',     'GW',     '(016977) 8525',    '1943-03-08',  'Male',    9999999074,  986433,       3,             75),
  (76,    'Mr',   'John',     'Walsh',        '94',         'High Hill',        'Galway',     'GW',     '0816 939 6772',    '1939-04-29',  'Male',    9999999075,  684434,       3,             76),
  (77,    'Mr',   'Tim',      'Walsh',        '3',          'SeaView Road',     'Galway',     'GW',     '0890 902 2134',    '1998-07-21',  'Male',    9999999076,  894313,       4,             77),
  (78,    'Mr',   'John',     'Kelly',        '12',         'Talbot Road',      'Galway',     'GW',     '0809 641 0596',    '1995-03-16',  'Male',    9999999077,  864513,       4,             78),
  (79,    'Mr',   'Michael',  'McGowan',      '100',        'Low Street',       'Galway',     'GW',     '0800 423784',      '1983-02-04',  'Male',    9999999078,  354681,       4,             79),
  (80,    'Mr',   'Jim',      'Murphy',       '27',         'Mallow View',      'Galway',     'GW',     '(01515) 20694',    '1986-12-30',  'Male',    9999999079,  861314,       4,             80),
  (81,    'Mr',   'Bill',     'Murphy',       '53',         'Douglas Road',     'Galway',     'GW',     '07624 601614',     '1969-07-19',  'Male',    9999999080,  684131,       5,             81),
  (82,    'Mr',   'Enda',     'O''Brien',     '93',         'SeaView Road',     'Galway',     'GW',     '0800 465805',      '1989-12-27',  'Male',    9999999081,  198445,       1,             82),
  (83,    'Mr',   'Philip',   'O''Connor',    '60',         'SandyMill Road',   'Galway',     'GW',     '055 7327 4627',    '1948-07-27',  'Male',    9999999082,  986413,       2,             83),
  (84,    'Mr',   'Philip',   'Walsh',        '75',         'Mallow View',      'Galway',     'GW',     '070 6741 6905',    '1955-01-12',  'Male',    9999999083,  864147,       3,             84),
  (85,    'Mr',   'Tim',      'Smith',        '84',         'Douglas Road',     'Galway',     'GW',     '076 5439 2425',    '1975-09-26',  'Male',    9999999084,  651534,       3,             85),
  (86,    'Mr',   'James',    'O''Brien',     '38',         'Talbot Road',      'Galway',     'GW',     '(016977) 7671',    '1988-10-22',  'Male',    9999999085,  584135,       3,             86),
  (87,    'Mr',   'Jack',     'O''Brien',     '82',         'SandyMill Road',   'Galway',     'GW',     '0301 776 8869',    '1937-03-30',  'Male',    9999999086,  854144,       4,             87),
  (88,    'Mr',   'Luke',     'O''Sullivan',  '17',         'Douglas Road',     'Galway',     'GW',     '(01027) 44276',    '1964-03-10',  'Male',    9999999087,  643133,       4,             88),
  (89,    'Mr',   'Enda',     'O''Brien',     '73',         'High Street',      'Galway',     'GW',     '(012913) 24672',   '1954-07-29',  'Male',    9999999088,  841351,       4,             89),
  (90,    'Mr',   'Philip',   'McGowan',      '93',         'Douglas Road',     'Galway',     'GW',     '07954 694224',     '1962-05-03',  'Male',    9999999089,  653518,       4,             90),
  (91,    'Mr',   'James',    'O''Sullivan',  '67',         'Summer Hill',      'Galway',     'GW',     '076 9759 9831',    '1998-03-13',  'Male',    9999999090,  964154,       5,             91),
  (92,    'Mr',   'Jim',      'O''Sullivan',  '41',         'High Hill',        'Limerick',   'LK',     '(01649) 794806',   '1950-06-25',  'Male',    9999999091,  684344,       1,             92),
  (93,    'Mr',   'James',    'O''Connor',    '26',         'High Street',      'Limerick',   'LK',     '07624 844651',     '1970-02-19',  'Male',    9999999092,  864313,       2,             93),
  (94,    'Mr',   'James',    'Kelly',        '44',         'Mallow View',      'Limerick',   'LK',     '076 2969 2315',    '1995-11-18',  'Male',    9999999093,  843147,       3,             94),
  (95,    'Mr',   'Michael',  'Kelly',        '50',         'Low Street',       'Limerick',   'LK',     '(015144) 77262',   '1936-11-04',  'Male',    9999999094,  684311,       3,             95),
  (96,    'Mr',   'Bill',     'McGowan',      '80',         'SeaView Road',     'Limerick',   'LK',     '070 7594 8865',    '1978-06-14',  'Male',    9999999095,  241843,       3,             96),
  (97,    'Mr',   'Jim',      'McGowan',      '100',        'Douglas Road',     'Limerick',   'LK',     '07977 658224',     '1954-02-04',  'Male',    9999999096,  841867,       4,             97),
  (98,    'Mr',   'Bill',     'O''Brien',     '27',         'Talbot Road',      'Limerick',   'LK',     '0800 719 4863',    '1941-03-23',  'Male',    9999999097,  845135,       4,             98),
  (99,    'Mr',   'John',     'Smith',        '13',         'Talbot Road',      'Limerick',   'LK',     '0500 772606',      '1992-11-09',  'Male',    9999999098,  864177,       4,             99);

UNLOCK TABLES;