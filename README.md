# Redistricting Simulator


http://redistricting.lls.edu

This application helps in the creation of new districts for a given State depending on user constraints.
The constraints that are supported include: 
Political Fairness
Compactness
Population Equality
  
The districts are created via a Region a Growing algorithm. The number of seed presents is determined by State law. The user has the option to select which precincts will be the seeds, Select the presents from where the corresponding representative lives or they can be randomly selected. 
A high-level view of the algorithm is as follows:
* Presents are selected in a Round-robin approach; Each of the Districts is allowed to get a precinct at a time.
* Each precinct determines what neighboring precincts are available (they were previously computed and are stored in a hashmap for faster computing where key= precinct value= list of(neighboring precincts) ) 
* Determines adding which of the neighboring precincts would result in a more favorable outcome determined by the user given constraints
* Add the new precinct into its district
* Updates the precincts that are new neighboring possibilities to its list of neighboring precincts (neighbors of the newly added precinct) 
  
  
When a state is selected the following screen appears: 
  
    
![picture alt](https://i.imgur.com/x7TClDv.png)
  
  
  
  
Political fairness goal is to attempt to give proportional representation to each constituent. (i.e) if 40% historically voted for one party and 60%voted for the other, attempt to give 40% of the power to the party that was voted for by 40% of the population
Compactness attempts to give precedence to precincts with a geographical midpoint closer to the seed precinct than to other ones that might be further away.
Population equality aims to distribute the population numbers equally amongst districts.
  
## Here is our program in action 
![picture alt](https://i.imgur.com/ZWwWfiX.gif)
  
  
  
  
Hovering over a given precinct shows more in-depth information. 
After the simulation is run information for each of hte new districts is displayed.
  
## Tools Used:

* Spring Boot
* Leaflet
* Java
* JavaScript
* HTML
* CSS
