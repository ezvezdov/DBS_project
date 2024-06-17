import csv
import string
import random
import datetime
import math

insert_file = open("../insert.sql","w")

# ID GENERATE
def generate_id():
    alphabet = string.digits + string.ascii_letters + string.ascii_uppercase
    return ''.join(random.choice(alphabet) for i in range(16))

# EMAIL GENERATE
def generate_email():
    email_domain = ['gmail', 'yahoo', 'hotmail', 'outlook']
    email_tlds = ['com', 'net', 'org', 'edu', 'gov']

    username = ''.join(random.choices(string.ascii_lowercase + string.digits, k=8))
    domain_name = random.choice(email_domain)
    tld = random.choice(email_tlds)

    email = f'{username}@{domain_name}.{tld}'
    return email

# DATE GENERATE
def generate_date():
    start_date = datetime.date(1960, 1, 1)
    end_date = datetime.date(2023, 12, 31)

    delta = end_date - start_date
    random_days = random.randint(0, delta.days)

    return start_date + datetime.timedelta(days=random_days)

# TIME GENERATE
def generate_time():
    return datetime.time(random.randint(0, 23), random.randint(0, 59), random.randint(0, 59))

# COST GENERATE
def generate_cost():
    return round(random.uniform(10, 100), 2)

# STADIUM_SECTOR GENERATE
def generate_sector():
    sector_letter = random.choice(string.ascii_uppercase)
    sector_number = random.randint(1, 100)
    sector_name = sector_letter + str(sector_number)
    return sector_name

# MEDIA_LINK GENERATE
def generate_media():
    media = ["facebook","twitter","instagram", "LinkedIn", "TikTok", "YouTube", "Pinterest", "Snapchat", "Reddit", "Tumblr", "Telegram", "Flickr", "Twitch", "Discord"]
    return "https://" + random.choice(media).lower() + ".com/" + generate_id()

# SPEED GENERATE
def generate_speed():
    return round(random.uniform(10, 100), 2)

# SHOOTING GENERATE
def generate_shooting():
    return random.randint(1, 100)


stadiums = []
# STADIUMS GENERATE
def generate_Stadium():
    global stadiums
    with open('stadiums.csv', 'r') as file:
        csv_reader = csv.reader(file)
        raw_stadiums = [row for row in csv_reader]
        for stadium_name, stadium_city,stadium_country in raw_stadiums:
            insert_file.write("INSERT INTO Stadium VALUES (E'{}', E'{}', E'{}');\n".format(stadium_name.title(),stadium_city.title(),stadium_country.title()))
            stadiums.append([stadium_name.title(),stadium_city.title(),stadium_country.title()])



persons = []
person_male = []
person_female = []
# PERSON GENERATE
def generate_Person_Names():
    # FEMALE NAMES
    names_female_file = open("names_female.txt", "r")
    names_female = names_female_file.read().split("\n")[:-1]
    names_female_file.close()

    # MALE NAMES
    names_male_file = open("names_male.txt", "r")
    names_male = names_male_file.read().split("\n")[:-1]
    names_male_file.close()

    # SURNAMES
    surnames_file = open("surnames.txt", "r")
    surnames = surnames_file.read().split(",\n")[:-1]
    surnames_file.close()

    def generate_person_info():
        names = [random.choice(names_male)]
        sex = "M"
        surname = random.choice(surnames)

        if random.random() > 0.5:
            names = [random.choice(names_female)]
            sex = "F"

        # several names
        while random.random() < 0.05:
            if sex == "F":
                name_f = random.choice(names_female)
                if name_f not in names:
                    names.append(name_f)
            else:
                name_m = random.choice(names_male)
                if name_m not in names:
                    names.append(name_m)

        # generate unique email
        email = generate_email()
        while email in persons:
            email = generate_email()

        return email,names,surname,sex

    for i in range(0,1000):
        email, names,surname,sex = generate_person_info()
        persons.append(email)
        if sex == "M":
            person_male.append(email)
        else:
            person_female.append(email)

        insert_file.write("INSERT INTO Person VALUES (E'{}', E'{}', E'{}');\n".format(email,surname,sex))
        for name in names:
            insert_file.write("INSERT INTO Names VALUES (E'{}', E'{}');\n".format(email,name))

def generate_Login_Site():
    usernames = []
    used_emails = []
    for i in range(0,math.floor(0.34 * len(persons))): 
        # generate unique username
        username = generate_id()
        while username in usernames:
            username = generate_id()
        usernames.append(username)

        password = generate_id()
        
        insert_file.write("INSERT INTO Login VALUES (E'{}', E'{}');\n".format(username,password))

        email = random.choice(persons)

        # Check unique of emails that has account
        while email in used_emails:
            email = random.choice(persons)
        
        used_emails.append(email)

        insert_file.write("INSERT INTO Site VALUES (E'{}', E'{}');\n".format(email,username))



ticket_ids = []
def generate_Viewer_Ticket_Purchase():

    viewers_ids = []
    viewers_amount = math.floor(random.uniform(0.4, 0.7) * len(persons))

    # VIEWER
    for i in range(0, viewers_amount):
        viewer_email = random.choice(persons)
        persons.remove(viewer_email)

        viewer_id = generate_id()
        while viewer_id in viewers_ids:
            viewer_id = generate_id()
        
        viewers_ids.append(viewer_id)
        
        insert_file.write("INSERT INTO Viewer VALUES (E'{}', E'{}');\n".format(viewer_email,viewer_id))
    
    # TICKET
    transactions = []
    tickets_amount = viewers_amount * 2
    for i in range(0,tickets_amount):
        ticket_id = generate_id()
        while ticket_id in ticket_ids:
            ticket_id = generate_id()
        ticket_ids.append(ticket_id)

        transaction_id = generate_id()
        while transaction_id in transactions:
            transaction_id = generate_id()
        transactions.append(transaction_id)
        
        sector = generate_sector()
        cost = generate_cost()
        
        
        if i < len(viewers_ids):
            insert_file.write("INSERT INTO Ticket (ticketID, transactionID, sector, cost) VALUES (E'{}', E'{}',E'{}', {});\n".format(ticket_id,transaction_id,sector,cost))
            insert_file.write("INSERT INTO Purchase VALUES (E'{}', E'{}');\n".format(viewers_ids[i],ticket_id))
        else:
            insert_file.write("INSERT INTO Ticket (ticketID, sector, cost) VALUES (E'{}',E'{}', {});\n".format(ticket_id,sector,cost))
    
    
def generate_Staff_Work_Sale():
    positions_list = ["Software Developer", "Sales Manager", "Marketing Specialist", "Data Analyst", "Human Resources Manager", "Accountant", "Graphic Designer", "Project Manager", "Operations Manager"]


    employees_amount = math.floor(len(persons) * random.uniform(0.05, 0.1))
    employee_ids = []

    # STAFF
    for i in range(0, employees_amount):
        email = random.choice(persons)
        persons.remove(email)

        position = random.choice(positions_list)

        employeeID = generate_id()
        while employeeID in employee_ids:
            employeeID = generate_id()
        
        employee_ids.append(employeeID)


        insert_file.write("INSERT INTO Staff VALUES (E'{}', E'{}',  E'{}');\n".format(email,employeeID, position))
    
    # WORK
    works = []
    for i in range(len(employee_ids)):
        work_employeeID = employee_ids[i]
        while True:

            work_stadium = random.choice(stadiums)
            if [work_employeeID,work_stadium] in works:
                continue
            works.append([work_employeeID,work_stadium])
            insert_file.write("INSERT INTO Work VALUES (E'{}', E'{}', E'{}', E'{}');\n".format(work_employeeID, work_stadium[0],work_stadium[1],work_stadium[2]))
            
            if random.random() > 0.3: break
    
    # SALE
    for i in range(len(ticket_ids)):
        sale_ticket_id = ticket_ids[i]
        sale_stadium = random.choice(stadiums)
        insert_file.write("INSERT INTO Sale VALUES (E'{}', E'{}', E'{}', E'{}');\n".format(sale_ticket_id, sale_stadium[0],sale_stadium[1],sale_stadium[2]))
    
biathlete_ids = []
biathlete_male = []
biathlete_female = []
def generate_Biathlete_Media_Stats_Teammates():
    global biathlete_ids

    for i in range(len(persons)):
        biathlete_email = persons[i]
        biathlete_id = generate_id()
        biathlete_ids.append(biathlete_id)

        if biathlete_email in person_male:
            biathlete_male.append(biathlete_id)
        else:
            biathlete_female.append(biathlete_id)

        insert_file.write("INSERT INTO Biathlete VALUES (E'{}', E'{}');\n".format(biathlete_email,biathlete_id))

        # MEDIA
        while random.random() > 0.5:
            insert_file.write("INSERT INTO Media VALUES (E'{}', E'{}');\n".format(biathlete_id,generate_media()))

        # STATS
        insert_file.write("INSERT INTO Stats VALUES (E'{}', {:.2f}, {});\n".format(biathlete_id,generate_speed(), generate_shooting()))
    
    for i in range(len(biathlete_ids)):
        biathlete_teammate = biathlete_ids[i]
        biathlete = random.choice(biathlete_ids)
        if biathlete == biathlete_teammate: continue
        insert_file.write("INSERT INTO Teammates  VALUES (E'{}', E'{}');\n".format(biathlete_teammate,biathlete))
    
competition_ids = []
def generate_Competition_Holder_Leaderboard_Results_Finish():
    competition_types = ["MEN 20 KM INDIVIDUAL", "WOMEN 15 KM INDIVIDUAL", "MEN 10 KM SPRINT",
                        "WOMEN 7.5 KM SPRINT", "MEN 12.5 KM PURSUIT", "WOMEN 10 KM PURSUIT",
                        "MEN 15 KM MASS START", "WOMEN 12.5 KM MASS START"
                        ]
    
    for i in range(0,300):
        competition_id = generate_id()
        while competition_id in competition_ids:
            competition_id = generate_id()
        competition_ids.append(competition_id)

        competition_type = random.choice(competition_types)
        competition_date = generate_date()
        competition_time = generate_time()

        insert_file.write("INSERT INTO Competition VALUES (E'{}', E'{}', E'{}', E'{}');\n".format(competition_id,competition_type,competition_date,competition_time))

        holder_stadium = random.choice(stadiums)
        insert_file.write("INSERT INTO Holder VALUES (E'{}', E'{}', E'{}', E'{}');\n".format(competition_id,holder_stadium[0],holder_stadium[1],holder_stadium[2]))
    
        leaderboard_biathlets = biathlete_male
        if "WOMEN" in competition_type:
            leaderboard_biathlets = biathlete_female
        
        random.shuffle(leaderboard_biathlets)

        biathletes_in_competition = math.floor(len(leaderboard_biathlets) * 0.5)
        for i in range(biathletes_in_competition):
            recordID = generate_id()
            insert_file.write("INSERT INTO Leaderboard VALUES ({}, E'{}', E'{}', E'{}');\n".format(i+1,competition_id,leaderboard_biathlets[i],recordID))

            insert_file.write("INSERT INTO Results VALUES (E'{}', E'{}');\n".format(competition_id,recordID))

            insert_file.write("INSERT INTO Finish VALUES (E'{}', E'{}');\n".format(leaderboard_biathlets[i],recordID))




generate_Stadium()
generate_Person_Names()
generate_Login_Site()
generate_Viewer_Ticket_Purchase()
generate_Staff_Work_Sale()
generate_Biathlete_Media_Stats_Teammates()
generate_Competition_Holder_Leaderboard_Results_Finish()

insert_file.close()
