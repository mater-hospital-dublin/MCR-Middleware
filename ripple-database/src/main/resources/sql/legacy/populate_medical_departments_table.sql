LOCK TABLES poc_legacy.medical_departments WRITE;

INSERT INTO poc_legacy.medical_departments
  (id,      department)
VALUES
  (1,       'NCHCD SJH DublinMids'),
  (2,       'SVH MMH DublinEast'),
  (3,       'South SouthWest'),
  (4,       'West NorthWest'),
  (5,       'Childrens');

UNLOCK TABLES;