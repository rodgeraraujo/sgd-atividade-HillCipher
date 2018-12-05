import random 
import math 
import numpy as np

#make encryption matrix

X = np.random.randint(1, 26, size=(2, 2))

def det_checker(A): 
    if ((np.linalg.det(A))%2 != 0) or ((np.linalg.det(A))%13 != 0): 
        return A 
    else:
        A = np.random.randint(1, 26, size=(2, 4))
        det_checker(A) 

det_checker(X) 

#prepare the string for computation 
N = 2
unencrypted_msg = str("hello world").replace(" ", "")

if len(unencrypted_msg)%2 != 0: 
    unencrypted_msg = unencrypted_msg + unencrypted_msg[-1] 

num_list = [ord(char) - 96 for char in unencrypted_msg]

num_vector = [num_list[i:i+N] for i in range(0, len(num_list), N)]

encrypted_list = []
for j in range(len(num_vector)): 
    encrypted_list.append(np.matmul(det_checker(X), num_vector[j]))
    

encrypted_modlist = [] 
for k in range(len(num_vector)): 
    encrypted_modlist.append(encrypted_list[k]%26)
    
total_list = [] 
for a in encrypted_modlist: 
    for b in a: 
        total_list.append(b)
print(total_list)

final = [] 
for num in total_list: 
    final.append(chr(num + 96))

final_encrypt = "".join(final)

print(final_encrypt)
