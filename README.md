# NightClubs API

To run it on your local machine, follow the steps bellow:

1) Open project with Maven
2) Run sql script from src/main/resources (you can run it in console ot via Workbench)
3) Run the app

Documentation

Note: you can export Postamn collection to test API from src/main/resources

Endpoints:

1) Create night club with name

  Params: name

  If club with such name already exists, throws exception
  
  Otherwise creates new night club with name from parameter

2) Create visitor with name

  Params: name

  If visitor with such name already exists, throws exception
  
  Otherwise creates new visitor with name from parameter

3) Create club visit

  Params: visitorName, clubName

  Creates visit of visitor with name visitorName to club with name clubName
  
  If such visit already exists, throws an exception
  
  If one or both of visitor or/and club with such names have not been created yet, creates one or both
  
  If exist, just adds club or/and visitor into list of clubs or/and visitors

4) Get visitor by name

  Params: name

  Return visitor dto with name from params (Visitor DTO contains id, name and list of names of visited clubs)
  
  If such does not exist, throws an exception

5) Get night club by name

  Params: name

  Return night club dto with name from params (Night club DTO contains id, name and list of names of visitors)
  
  If such does not exist, throws an exception

6) Get clubs not visited by user with name

  Params: name

  Returns list of night club dto, that visitor with such name has not visited
  
  If visitor with such name does not exist, throws an exception

7) Get clubs visited by user with name

  Params: name

  Returns list of night club dto, that visitor with such name has visited
  
  If visitor with such name does not exist, throws an exception

8) Get visitors of club with name

  Params: name

  Returns list of visitor dto, that visited night club with such name
  
  If night club with such name does not exist, throws an exception
  
