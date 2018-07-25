import os
import psycopg2
import urlparse

urlparse.uses_netloc.append("postgres")
url = urlparse.urlparse(os.environ["postgres://ndgxpqgbidmxgp:6KK5yKaZueccpGA8fahFD1KnqM@ec2-54-235-76-253.compute-1.amazonaws.com:5432/d96s3h5n8ld1q2"])

conn = psycopg2.connect(
    database=url.path[1:],
    user=url.username,
    password=url.password,
    host=url.hostname,
    port=url.port
)