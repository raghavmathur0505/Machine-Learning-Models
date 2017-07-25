import numpy as np
import cv2
import sys

k, input_name ,output_name = sys.argv[1:]
# input image and reshape for the computation
input_img = cv2.imread(input_name)
reshape_img = input_img.reshape((-1, 3))

# convert to np.float32
reshape_img = np.float32(reshape_img)

# define criteria, number of clusters(K) and apply kmeans()
criteria_kmeans = (cv2.TERM_CRITERIA_EPS + cv2.TERM_CRITERIA_MAX_ITER, 10, 1.0)
k_value = int(k)
ret,label,center = cv2.kmeans(reshape_img, k_value, None, criteria_kmeans, 10, cv2.KMEANS_RANDOM_CENTERS)

# Now convert back into uint8(uses numpy), and then into original image
center = np.uint8(center)
result_img_raw = center[label.flatten()]
result_img_final = result_img_raw.reshape(input_img.shape)

#cv2.imshow('result_img_final', result_img_final)
cv2.imwrite(output_name, result_img_final)
cv2.waitKey(0)
cv2.destroyAllWindows()