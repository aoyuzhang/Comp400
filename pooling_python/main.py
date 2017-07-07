"""
THIS IS A SIMPLE EXAMPLE OF THE IMPLICATIONS OF
THE CNN FUNCTIONS FROM final_functions.py MODULE
"""

# Import module with preexisting functions
import final_functions  # Preexisting functions
import data_examples    # Preexisting matrices

# Image with X
image = data_examples.mat10x10.slash
cnn_filter = data_examples.mat5x5.slash

# Convolved function
def convolved(image,cnn_filter):
    # image : the image in form of 2D matrix
    # cnn_filter : 2D matrix used (must be a square filter)
    # filter_size : len(cnn_filter[0])
    # FILTER AND IMAGE MUST BE BOTH SQUARE
    # CONVOLVED STACK
    convolved_stack = final_functions.convolution(image,len(cnn_filter[0]))
    # FILTERED STACK
    convolved_final, averaged_matrices = final_functions.conv_average(convolved_stack, cnn_filter,len(cnn_filter[0]))
    # AVERAGED STACK
    final_result = final_functions.matrix_avg(averaged_matrices,len(cnn_filter[0]), len(image[0]))
    return final_result

# EXAMPLE OF USE

print("TESTING CONVOLUTION...\n\n")
matrix = convolved(image, cnn_filter)

# PRINTS OUT CONVOLUTED LAYER
print("\nORIGINAL IMAGE:\n")
final_functions.print2dmatrix(image)        # PRINTS OUT ORIGINAL IMAGE
print("\nFILTER:\n")
final_functions.print2dmatrix(cnn_filter)   # PRINTS OUT FILTER THAT WAS USED
print("\nCONVOLVED MATRIX:\n")
final_functions.print2dmatrix(matrix)       # PLEASE NOTE THAT THERE MAY BE MATHEMATICAL ERRORS


matrix2 = convolved(matrix, cnn_filter)
print("\nFILTER USED:\n")
final_functions.print2dmatrix(cnn_filter)   # PRINTS OUT FILTER THAT WAS USED
print("\nCONVOLVED MATRIX:\n")
final_functions.print2dmatrix(matrix2)

# TESTING RELU LAYER
#print("\nTESTING RELU LAYER...\n")
relu_matrix = data_examples.values.return10x10(-100,100)
print("\nORIGINAL IMAGE:\n")
final_functions.print2dmatrix(relu_matrix)
#relu_matrix = final_functions.relu(relu_matrix)
#print("PROCESSED IMAGE")
#final_functions.print2dmatrix(relu_matrix)

# TESTING POOLING
print("\nTESTING POOLING LAYER...\n")
relu_matrix = final_functions.fill0s(relu_matrix,7) # make a larger matrix with 0s
final_functions.print2dmatrix(relu_matrix)
print("FINISHED fill0s()")
pooling_matrix = final_functions.pooling(relu_matrix,7) # subdivise the matrix into smaller matrices
final_functions.print3dmatrix(pooling_matrix)
print("\nCONVOLVED\n")
max_list = final_functions.maxPooling(pooling_matrix)       # Return as 1D list the maximums of each matrix
max_list2d = final_functions.pooling2dReturn(max_list,7,15) # Return as 2D matrix: end of pooling layer
print("\nPOOLING LAYER COMPLETE\n")
final_functions.print2dmatrix(max_list2d)
