<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Service.AuthenticationService" %>    
<%
    	// Check that user is logged in
       if (!AuthenticationService.isLoggedIn(session)) {
       	response.sendError(403); // not authenticated
       }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-theme.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/data-table-jui.css" />
		<title>Help Page</title>
	</head>
	<body>

		<div class="main">
			<jsp:include page="/header.jsp"/>
			
			<div class="ui-corner-all box">
				<h3>Common Computer Problems</h3>
				<p>With constantly advancing technology, it's difficult to keep abreast of all the constantly evolving changes. Just about everyone can relate to having PC problems at some point. Here's a quick list of the most common problems and some basic advice as to how serious they are. <br />
					<br />
					<h5>1.	Slow Computer</h5>
					This usually happens when you have had a computer for a year or two. The most common cause is a lack of memory space. Over time, your computer collects all kinds of bits of information and stores it in its memory. An easy way to manage this is to do a regular disk clean-up. This gets rid of temporary and deleted files and frees up disk space, making your computer operate more efficiently. Also, be aware of the file sizes that you are storing on your PC. Videos, music and photographs can take up huge volumes of memory and affect the operating speed of your computer. A good idea is to store large files on an external hard drive that you can simply plug in when you want to access the files. <br />
					<br />
					Computers are also not designed to have a very long lifespan. With advances in technology, it is common to upgrade computers every two or three years. If your computer is getting increasingly slower, it could mean that in addition to a lack of memory space, your operating system is failing. Take a slow computer as a warning sign; back up your data and possibly even start shopping around for a new computer. <br />
					<br />
					<h5>2.	Computer Can't Read a CD or Disk Drive</h5>
					The CD could be damaged or scratched, or there could be a problem with the disk drivers. Test the disk drive by inserting an alternate CD and see if it plays. If it does then the problem is not with your computer but rather with the CD that you are trying to read. <br />
					<br />
					<h5>3.	Can't Delete Files</h5>
					Sometimes files created by other users have write protection on them. This will prevent you from editing or deleting the documents. Some files also will not allow you to delete a document if you have it open at the time. Try closing the document and finding it on your drive before trying to delete it. To delete program files, you can't simply delete the icon on your screen. You will have to access your program manager and follow the process to uninstall the program. <br />
					<br />
					<h5>4.	Screen Freeze</h5>
					This frequently occurs when browsing the Web. Most often, the problem is not with your computer but rather either the internet connection or website host. Sometimes when a website has large volumes of traffic, a page can freeze because the system is overloaded. This can be very frustrating especially if you are trying to make an online purchase or payment. Often, if you refresh the page or try re-enter the website, the problem will be solved. <br />
					<br />
					<h5>5.	Program Not Responding</h5>
					This sometimes happens with network connections or when trying to save large documents. A program that is not responding simply means that it has frozen. Often, if you close down the program and re-open it, this solves the error. Depending on what program you are working in at the time, it could be cause for concern. If this starts to happen frequently, it would be a good idea to have your computer checked out by an IT professional. It could mean that your operating system is starting to fail or your drivers need to be updated. A technician will be able to evaluate if it is a serious problem or not.<br />
					<br />
					<h5>6.	Document Not Printing</h5>
					It may sound silly but one of the most common reasons a document doesn't print is because the printer is either not connected to the computer or not switched on. If you have checked both of these and the document still brings up an error, it could mean that there is either a problem with your printer drivers or the actual document. Some documents, even though displayed on a screen, cannot be printed unless you have the correct software installed on your computer. A document could also be corrupt and require reformatting before you are able to print it. <br />
					<br />
					<h5>7.	Not Able to Read External Devices</h5>
					Most plug-in devices such as printers, scanners and external hard drives now come with generic USB ports. As you plug them in, the computer automatically searches for the relevant software and installs it so the two devices can speak to each other. Occasionally, your computer may not recognize the device or software and bring up an error message that it cannot be read. Sometimes, you may need to manually install the drivers by inserting a CD or download the software from the Internet. Check that the drivers are compatible with the operating system you have on your computer.<br />
					<br />
					<h5>8.	No Network Connection</h5>
					This is seldom a problem with your specific computer. Rather, it is usually that the network connection has been interrupted or failed. Close down the program and then try to reconnect. If after a while you still cannot connect, contact your service provider. Often, this is a broader network problem. If there is no problem with your service provider but you still cannot connect, the problem could be with your computer drivers or software. Your best option is to get it checked out by an IT professional. <br />
					<br />
					<h5>9.	Computer Viruses and Spyware</h5>
					It's absolutely essential when operating a computer to have an up-to-date quality anti-virus and spyware program running on your computer. There is never a 100% guarantee that your computer will be protected but at least it dramatically reduces the risk. A good antivirus will regularly scan your computer and all incoming emails and websites for possible threats and delete them before they become a problem. <br />
					<br />
					<h5>10. Not Enough Memory</h5>
					This usually occurs when your computer is old or you have not cleaned out your files. Try to run through your data and see what you don't need or transfer the information onto an external drive to free up memory space.</p>
				<p>Article Source: http://www.abcarticledirectory.com - Published under a Creative Commons Attribution-No Derivative Works 3.0 Unported License</p>
			</div>
		</div>
	</body>
</html>