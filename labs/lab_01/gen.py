import datetime
import csv
import string

from random import randint, choice
from faker import Faker
from uuid import uuid4


def genPhoneNum():
    return randint(7000_000_0000, 7999_999_9999)


def genStrPhoneNum(number):
    d2 = number % 100
    number //= 100
    d4 = number % 100
    number //= 100
    d7 = number % 1000
    number //= 1000
    d10 = number % 1000
    return "+7-" + str(d10) + "-" + str(d7) + "-" + str(d4) + "-" + str(d2)


def readIdsCsv(filename):
    ids = []
    with open(filename, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            ids.append(row['id'])

    return ids


def readIdsNamesCsv(filename):
    res = []
    with open(filename, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            res.append({'id': row['id'], 'name': row['name']})

    return res


def genManufactures():
    names = [line.strip() for line in open("data/txt/manufacturers.txt", "r")]

    with open('data/manufacturers.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for name in names:
            id = uuid4()
            writer.writerow({'id': id, 'name': name})

def genCategories():
    names = [line.strip() for line in open("data/txt/categories.txt", "r")]

    with open('data/categories.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for name in names:
            id = uuid4()
            writer.writerow({'id': id, 'name': name})


def genGuitarTypes():
    names = [line.strip() for line in open("data/txt/guitar_types.txt", "r")]

    with open('data/guitar_types.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for name in names:
            id = uuid4()
            writer.writerow({'id': id, 'name': name})


def genMaterials():
    names = [line.strip() for line in open("data/txt/materials.txt", "r")]

    with open('data/materials.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for name in names:
            id = uuid4()
            writer.writerow({'id': id, 'name': name})


def genSoundPickups():
    names = [line.strip() for line in open("data/txt/sound_pickups.txt", "r")]

    with open('data/sound_pickups.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for name in names:
            id = uuid4()
            writer.writerow({'id': id, 'name': name})


def genOrderStatuses():
    names = [line.strip() for line in open("data/txt/order_statuses.txt", "r")]

    with open('data/order_statuses.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for name in names:
            id = uuid4()
            writer.writerow({'id': id, 'name': name})


def genCustomers():
    cntRecords = 1000

    with open('data/customers.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'first_name', 'last_name', 'birth_date', 'email', 'phone']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for i in range(cntRecords):
            phoneNum = genPhoneNum()
            id = uuid4()
            writer.writerow({'id': id, 'first_name': fake.first_name(), 'last_name': fake.last_name(),
                             'birth_date': fake.date_between(datetime.date(1970, 1, 1), datetime.date(2010, 12, 31)),
                             'email': fake.email(), 'phone': genStrPhoneNum(phoneNum)})


def genProductModels():
    def genProductModelValues(fieldValues):
        def genProductModelName(manufacturer):
            return manufacturer + ' ' + ''.join(choice(string.ascii_uppercase) for _ in range(3)) + \
                   '-' + ''.join(choice(string.digits) for _ in range(4))

        def genGuitarValues(fieldValues):
            fieldValues['type'] = choice(guitarTypeIds)
            fieldValues['manufacturer'] = manufacturer['id']
            fieldValues['body_material'] = choice(materialIds)
            fieldValues['neck_material'] = choice(materialIds)
            fieldValues['sound_pickup'] = choice(soundPickupIds)
            fieldValues['cnt_frets'] = choice([15, 18, 24])

        def genSynthesizerValues(fieldValues):
            fieldValues['manufacturer'] = manufacturer['id']
            fieldValues['cnt_keys'] = choice([44, 49, 61, 76])
            fieldValues['cnt_voices'] = randint(8, 48)
            fieldValues['cnt_styles'] = randint(100, 800)
            fieldValues['cnt_timbres'] = randint(100, 400)
            fieldValues['power'] = randint(1, 10)

        manufacturer = choice(manufacturers)
        category = choice(categories)

        fieldValues['id'] = uuid4()
        fieldValues['category_id'] = category['id']
        fieldValues['name'] = genProductModelName(manufacturer['name'])
        fieldValues['price'] = randint(5000, 25000)
        fieldValues['storage_cnt'] = randint(1, 20)
        fieldValues['color'] = choice(colors)

        if category['name'] == 'Гитары':
            genGuitarValues(fieldValues)
        elif category['name'] == 'Синтезаторы':
            genSynthesizerValues(fieldValues)
            print(fieldValues)

    cntRecords = 1000
    categories = readIdsNamesCsv('data/categories.csv')
    guitarTypeIds = readIdsCsv('data/guitar_types.csv')
    manufacturers = readIdsNamesCsv('data/manufacturers.csv')
    materialIds = readIdsCsv('data/materials.csv')
    soundPickupIds = readIdsCsv('data/sound_pickups.csv')
    colors = [line.strip() for line in open("data/txt/colors.txt", "r")]

    for m in manufacturers:
        print(m)

    with open('data/ProductModel.csv', 'w', newline='') as csvfile:
        fieldNames = ['id', 'category_id', 'name', 'price', 'storage_cnt', 'color', 'type', 'manufacturer',
                      'body_material', 'neck_material', 'sound_pickup',
                      'cnt_frets', 'cnt_keys', 'cnt_voices', 'cnt_styles', 'cnt_timbres', 'power']
        writer = csv.DictWriter(csvfile, fieldnames=fieldNames)
        writer.writeheader()

        for i in range(cntRecords):
            fieldValues = {field: None for field in fieldNames}
            genProductModelValues(fieldValues)
            writer.writerow(fieldValues)

def genOrders():
    cntRecords = 2500
    statuses = readIdsCsv('data/order_statuses.csv')
    customerIds = readIdsCsv('data/customers.csv')

    with open('data/Orders.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'customer_id', 'date', 'status']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for i in range(cntRecords):
            id = uuid4()
            writer.writerow({'id': id,
                             'customer_id': choice(customerIds),
                             'date': fake.date_between(datetime.date(2005, 1, 1), datetime.date(2021, 12, 31)),
                             'status': choice(statuses)})

def genOrder_ProductModel():
    cntRecords = 2500
    orderIds = readIdsCsv('data/Orders.csv')
    productModelIds = readIdsCsv('data/ProductModel.csv')

    with open('data/Order_ProductModel.csv', 'w', newline='') as csvfile:
        fieldnames = ['order_id', 'product_model_id', 'cnt_products']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for i in range(cntRecords):
            writer.writerow({'order_id': choice(orderIds),
                             'product_model_id': choice(productModelIds),
                             'cnt_products': randint(1, 20)})

if __name__ == "__main__":
    fake = Faker()
    genCategories()
    genManufactures()
    genGuitarTypes()
    genMaterials()
    genSoundPickups()
    genOrderStatuses()

    genCustomers()
    genProductModels()
    genOrders()
    genOrder_ProductModel()
