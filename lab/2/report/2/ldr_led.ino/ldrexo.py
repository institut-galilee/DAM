import serial
import matplotlib.pyplot as plt

plt.ion()
i=0

ser = serial.Serial('COM3',9600)
ser.close()
ser.open()
data = ser.readline()
splt=data.split()

if(len(splt)==1):
	fig,ax=plt.subplots()
	axs=[ax]
else:
	fig, axs = plt.subplots(len(splt), sharex=True)
	
while True:

	data = ser.readline()
	splt=data.split();
	for p in range(len(splt)):
		print(splt[p].decode(), end = ' ')
		axs[p].scatter(i, float(splt[p].decode()))
		#axs[p].yaxis.set_major_locator(plt.MaxNLocator(4))
		
	print()
	i += 1
	plt.show()
	plt.pause(0.0001)  # Note this correction
