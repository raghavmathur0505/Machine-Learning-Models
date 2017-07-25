import csv
import json
import math
import random
import sys


def sse(x, cluster_point):
    distance = 0
    temp_value = 0
    for j in cluster_point:
        for i in x:
            if (x[j][1] != j): continue
            temp_value = 1 - float(len((x[i][0]).intersection(x[j][0]))) / len((x[i][0]).union(x[j][0]))
            distance += math.pow(temp_value, 2)
    print '\nSSE: ', distance, ' k = ', len(cluster_point)

def sse_cluster(cluster_id):
    temp = 0
    for i in cluster_id:
        temp += math.pow(i, 2)
    return temp

k_value, initial_seeds, input_tweets, output_file = sys.argv[1:]

k_value = int(k_value)
out_file = open(output_file, 'w')
temporary = sys.stdout
sys.stdout = out_file
in_file = open(input_tweets)

X = {}
stopwords = ["a", "about", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone",
             "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an",
             "and", "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at",
             "back", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand",
             "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but",
             "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail",
             "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven", "else", "elsewhere",
             "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few",
             "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found",
             "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he",
             "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself",
             "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it",
             "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me",
             "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my",
             "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none",
             "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only",
             "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part",
             "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems",
             "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some",
             "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take",
             "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter",
             "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this",
             "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top",
             "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via",
             "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter",
             "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who",
             "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you",
             "your", "yours", "yourself", "yourselves", "the"]
stopwords = set(stopwords)
x = in_file.readline()
i = 0
while (x != ''):
    tweet_x = json.loads(x)
    tweet_x['text'] = set((tweet_x['text']).split(' ')).difference(stopwords)
    X.update({tweet_x['id']: [tweet_x['text'], -1]})
    x = in_file.readline()

out_file = open(initial_seeds, 'rt')
has_header = csv.Sniffer().has_header(out_file.read(1024))
out_file.seek(0)
in_csv = csv.reader(out_file)
if has_header:
    Attr_names = next(in_csv)
K = [int(i[0]) for i in in_csv]

temp = random.sample(K, k_value)
kpoint = {}
len_kpoint = len(temp)
for i in temp:
    kpoint.update({i: X[i]})

len_X = len(X)
in_file.close()
out_file.close()

dist = {}
centre = []
iter = 0
diff = []
old = []

for i in xrange(k_value):
    diff.append([-1])

print 'Iterations : ',
while (iter < 25 and any(h != 0 for h in diff)):
    print iter,
    # print diff
    old = []
    temp = X.iterkeys()
    for j in xrange(len_X):
        old.append(temp.next())

    for i in X:

        dist = 1
        min_dist = 2
        assign_id = 0
        for k_iter in kpoint:
            dist = 1 - float(len((X[i][0]).intersection(kpoint[k_iter][0]))) / len((X[i][0]).union(kpoint[k_iter][0]))
            if (dist < min_dist):
                min_dist = dist
                assign_id = k_iter
        X[i][1] = assign_id

    kpoint_temp = {}
    for k_iter in kpoint:
        dist = 1
        min_sse = 999999999
        assign_id = 0

        for i in X:
            if (X[i][1] != k_iter): continue
            temp_set = []

            for j in X:
                if (X[j][1] != k_iter): continue
                if (i == j): continue
                temp_set.append(1 - float(len((X[i][0]).intersection(X[j][0]))) / len((X[i][0]).union(X[j][0])))
            dist = sse_cluster(temp_set)

            if (dist < min_sse):
                min_sse = dist
                assign_id = i

        kpoint_temp.update({assign_id: X[assign_id]})

    kpoint_len = len(kpoint)
    kpoint_temp_ptr = kpoint_temp.__iter__()
    diff = []
    for j in kpoint:
        diff.append(j - kpoint_temp_ptr.next())
    kpoint = kpoint_temp
    iter += 1

print "\n"
abc = 0
print 'Cluster-ID   Tweet-IDs contained in the cluster(comma separated)',
for i in kpoint:
    print '\n', abc, '    ',
    abc += 1
    for j in X:
        if (i == X[j][1]):
            print j, ',',
sse(X, kpoint)
sys.stdout = temporary
out_file.close()
print '\n\n'
print 'Output is printed in the file named ', output_file
