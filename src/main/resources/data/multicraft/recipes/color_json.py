import os

colors = ['orange','magenta','light_blue','yellow','lime','pink','gray','light_gray','cyan','purple','blue','brown','green','red','black']

# feed in white version of json here
template = input()

if(os.path.isfile(template)):

    for color in colors:
        
        template_file = open(template,'r')

        new_file = open(template.replace('white', color),'w')

        for line in template_file:
            new_file.write(line.replace('white', color))

        template_file.close()

        new_file.close()
        print('Json File Generated: ' + template.replace('white', color))

    print('')
    print('Files Generated')
else:
    print('That file does not exist')
input()