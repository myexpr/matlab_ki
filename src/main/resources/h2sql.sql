CREATE TABLE DESTINATION (
  locationid INT PRIMARY KEY,
  city_name VARCHAR NOT NULL,
  region_code VARCHAR,
  country_code VARCHAR NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL
)