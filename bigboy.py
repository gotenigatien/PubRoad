from tbselenium.tbdriver import TorBrowserDriver
import time
from selenium.webdriver.common.keys import Keys
from pyvirtualdisplay import Display
a=0
while a == 0:
    a=1
    driver = TorBrowserDriver("/home/ngatseoya/Downloads/tor-browser_en-US")
    k = 0
    n=10
    while k <= 10:
        t=0
        while t < 3:
            passa = 0
            while passa == 0:
                try:
                    driver.execute_script('document.getElementsByClassName("ow_votebutton  ow_loggin_disabled voter_a_btn_term39")[0].click();')
                    time.sleep(2)
                    print('CONSOLE ==> CLICK()')
                    passa = 1
                except:
                    passa = 0
            passa=0
            
            while passa == 0:
                try:
                    driver.execute_script('document.getElementsByClassName("owt_success")[0]')
                    time.sleep(1)
                    print('CONSOLE ==> SUCCESS()')
                    passa = 1
                except:
                    passa = 0
            passa = 0
            
            while passa == 0:
                try:
                    driver.execute_script('document.getElementsByClassName("ow_vote_fancybox-item ow_vote_fancybox-close")[0].click();')
                    time.sleep(1)
                    print('CONSOLE ==> SUCCESS()')
                    passa = 1
                except:
                    passa = 0
            passa = 0
            t=t+1
        t=0  
        driver.close()
        time.sleep(10)
        k=k+1
        print('CONSOLE ==> VOTE '+str(k)+'/'+str(n)+' ou '+str(k)+'*3='+str(k*3))
                        
