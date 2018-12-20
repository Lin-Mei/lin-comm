#coding:utf-8
#pip3 install bs4 lxml
import urllib.request
from bs4 import BeautifulSoup
import json
import os
import codecs
domain="https://so.gushiwen.org"
shangxi_url="https://so.gushiwen.org/shiwen2017/ajaxshangxi.aspx?id="
class Shiwen(object):
    titile=""
    author=""
    source=""
    content=""
    yizhu=""
    shangxi=""

def getHtml(url):
    page = urllib.request.urlopen(url)
    htmlcode = page.read()
    html=htmlcode.decode('utf8')
    return html
def is_shangxi(tag):
    return tag.name=="div" and tag.has_attr('id') and tag.get('id').startswith("shangxi")
def is_shangxiquan(tag):
    return tag.has_attr('id') and tag.get('id').startswith("shangxiquan")
def get_shangxi(shici_id):
    url=shangxi_url+shici_id
    print(url)
    html=getHtml(url)
    if(html==''):
        return ""
    soup = BeautifulSoup(html,'lxml')
    text=""
    for t in soup.find("div","contyishang").find_all("p"):
        text+=t.get_text()
    return text
def parserShiwen(url):
    print("正在抓取："+url)
    shiwen=Shiwen()
    html=getHtml(url)
    soup = BeautifulSoup(html,'lxml')
    main3=soup.find("div","main3")
    cont=main3.find("div","cont")
    source=cont.find("p","source")
    # print(cont.find("h1").get_text())
    shiwen.titile=cont.find("h1").get_text()
    shiwen.author=source.find_all("a")[1].get_text()
    shiwen.source=source.find_all("a")[0].get_text()
    shiwen.content=cont.find("div","contson").get_text()

    contyishang=soup.find("div","contyishang")
    # [s.extract() for s in contyishang.find("strong")]
    shiwen.yizhu=str(contyishang.find_all("p"))

    shangxi=soup.find(is_shangxi)
    if(shangxi is not None):
        shici_id=shangxi.get("id").replace("shangxi","")
        shiwen.shangxi=get_shangxi(shici_id)
    return shiwen
def parserMain(html):
    soup = BeautifulSoup(html,'lxml')
    i=0
    for typecont in soup.find_all("div", "typecont"):
        for link in  typecont.find_all("a"):
            i=i+1
            url=domain+link.get("href")
            shiwen=parserShiwen(url)
            jstr = json.dumps(shiwen.__dict__,ensure_ascii=False)
            fileName="data/shiwen"+str(i)+".json"
            f=codecs.open(fileName,"w","utf-8-sig")
            f.write(jstr)
            f.close
def mkdir(path):
    if os.path.exists(path):
        print("remove forder:"+path)
        os.remove(path)
    os.makedirs(path)
    print("---  new folder...  ---"+path)
    print("---  OK  ---")

mkdir("data")
html = getHtml("https://so.gushiwen.org/gushi/tangshi.aspx")
parserMain(html)

# s=parserShiwen("https://so.gushiwen.org/shiwenv_f67ed771d1fa.aspx")
# jstr = json.dumps(s.__dict__,ensure_ascii=False)
# print(jstr)