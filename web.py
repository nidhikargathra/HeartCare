import urllib

f = urllib.urlopen("http://stackoverflow.com/questions/20767732/best-way-to-extract-text-e-g-articles-from-web-page")
s = f.read()

print s

f.close()