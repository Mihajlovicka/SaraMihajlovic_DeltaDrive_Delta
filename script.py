import pandas as pd
import mysql.connector
from mysql.connector import Error

# MySQL database connection settings
db_config = {
    "host": "localhost",  # Hostname or IP address
    "port": 3306,
    "user": "root",
    "password": "admin",
    "database": "deltadrive",
}

# Input CSV file
csv_file = "delta-drive.csv"

# Read the CSV file using pandas
df = pd.read_csv(csv_file)

# Establish a connection to the MySQL database
try:
    connection = mysql.connector.connect(**db_config)

    if connection.is_connected():
        cursor = connection.cursor()

        # Define the table schema
        create_table_query = """
        CREATE TABLE IF NOT EXISTS vehicle (
            id INT AUTO_INCREMENT PRIMARY KEY,
            brand VARCHAR(255),
            first_name VARCHAR(255),
            last_name VARCHAR(255),
            current_position POINT NOT NULL ,
            start_price DOUBLE,
            price_per_km DOUBLE,
            state INT 
        )
        """
        cursor.execute(create_table_query)

        # Commit the table creation
        connection.commit()
        i  = 0
        # Iterate through each row in the DataFrame and insert it into the MySQL database
        for _, row in df.iterrows():
#brand,firstName,lastName,latitude,longitude,startPrice,pricePerKM
            i+=1
            if i >= 20: break
            brand = row["brand"]
            firstName = row["firstName"]
            lastName = row["lastName"]
            latitude = row["latitude"]
            longitude = row["longitude"]
            try:
                start_price = float(row["startPrice"].replace("EUR", ""))
                price_per_km = float(row["pricePerKM"].replace("EUR", ""))
            except ValueError:
                # Handle non-numeric values, for example, set them to 0 or handle them as needed
                start_price = 0
                price_per_km = 0

            # Define the SQL query for inserting the data
            insert_query = (
                "INSERT INTO vehicle (brand, first_name, last_name, current_position, start_price, price_per_km, state) "
                "VALUES (%s, %s, %s, ST_GeomFromText('POINT(%s %s)'), %s, %s, %s)"
            )
            data = (brand, firstName, lastName, latitude, longitude, start_price, price_per_km,0)

            cursor.execute(insert_query, data)
            connection.commit()

        cursor.close()

except Error as e:
    print("Error:", e)

finally:
    if connection.is_connected():
        connection.close()

print("Data imported into MySQL.")

#get current location
#get 10 nearest free
#show
#pick
#accept or not
#drive
#end drive
#review
