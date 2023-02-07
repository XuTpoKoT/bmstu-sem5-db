import requests
from bs4 import BeautifulSoup as bs
import csv
import socks
import socket
import time
from fake_useragent import UserAgent
from TorCrawler import TorCrawler

URL_SHOP = "https://www.dj-store.ru"
URL_TEMPLATE = "https://www.dj-store.ru/oborudovanie/gitary/akusticheskie-gitary/drednouty/70700_fante-ft-d38-3ts.html"
URL_ACOUSTIC_GUITARS = "https://www.dj-store.ru/oborudovanie/gitary/akusticheskie-gitary/"
URL_ACOUSTIC_GUITAR1 = "https://www.dj-store.ru/oborudovanie/gitary/akusticheskie-gitary/drednouty/62666_baton-rouge-x11s_sd-cob.html"


def checkIP():
    ip = requests.get('http://checkip.dyndns.org').content
    soup = bs(ip, 'html.parser')
    print(soup.find('body').text)


def parseAcoustic(url=URL_ACOUSTIC_GUITARS):
    try:
        response = crawler.get(url, headers={'User-Agent': UserAgent().chrome})
    except:
        print("Не получилось спарсить инструмент")
        return

    tiletag = response.find(lambda tag: tag.name == 'div' and tag.get('class') == ['tile', 'clear'])
    if tiletag is None:
        print("Tile tag is None!")
        return

    i = 0
    for prodtag in tiletag.find_all(lambda tag: tag.name == 'div' and tag.get('class') == ['prod_li']):
        print("Product ")
        # print(prodtag.get('class'))
        if prodtag is None:
            print("Product tag is None!")
            continue

        imtag = prodtag.find(lambda tag: tag.name == 'a' and tag.get('class') == ['img'])
        if imtag is not None:
            print(URL_SHOP + imtag['href'])
            parseInstrument(URL_SHOP + imtag['href'])
        time.sleep(5)
        i += 1
        if i > 5:
            break

def parseInstrument(url=URL_ACOUSTIC_GUITAR1):
    try:
        response = crawler.get(url, headers={'User-Agent': UserAgent().chrome})
    except:
        print("Не получилось спарсить инструмент")
        return
    print("\n=======================================================")
    print(crawler.ip)

    for divtag in response.find_all('div', {'class': 'text product-tabs tab-specific hidden'}):
        for litag in divtag.find('ul').find_all('li'):
            print(litag.text)


if __name__ == "__main__":
    crawler = TorCrawler(ctrl_pass='toric')

    parseAcoustic()
