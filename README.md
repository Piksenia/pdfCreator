# pdfCreator
Generate static templates with dynamic attributes from java objects to create pdf (serverside).
 Templates based on html and css with thymeleaf specific attributes for dynamic number of elemets (for example how often to use template, list attributes within java object). 
 1) Initialize java objects and put into list
 2) Generate html with list of objects
 3) Convert rendered html
 
 For local start:
 - simple test with single java objects (hard coded in project), call http://localhost:8080/api/profile/piksenia or simple http://localhost:8080/api/profile/anyName
 - simple test with multiple java objects (hard coded as well with default values), call http://localhost:8080/api/profile/
 
 ATTENTION: Margin and padding in percent aren't supported by converter
