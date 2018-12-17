package com.example.Gerrymandering.domain;

import java.util.*;

public class MetricsManager {
    public Precinct getBestPrecinct(State state, Set<Precinct> possibleNeighnors, District currentDistrict, Precinct seedPrecinct, HashMap<String, Double> objectiveValues){
        Precinct BestPrecinct = new Precinct();
        double politicalFairness = objectiveValues.get("politicalFairness");
        double compactness = objectiveValues.get("compactness");
        double populationEquality = objectiveValues.get("populationEquality");

        politicalFairness = (politicalFairness / 100) /1.2;
        compactness = (compactness / 100) /1.2;
        populationEquality =(populationEquality / 100) /1.2;



        if((politicalFairness == 0) && (populationEquality == 0) && (compactness != 0)){
            Compactness compactness1 = new Compactness();
            List<Precinct> fistList = compactness1.compactness(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = fistList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(fistList.subList(0, (int)Math.ceil(firstListSize * (1-compactness) )));
            Collections.shuffle(subItems);
            BestPrecinct = subItems.get(0);
        }
        else if ((politicalFairness == 0) && (populationEquality != 0) && (compactness == 0)){
            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> fistList = populationEquality1.populationEquality(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = fistList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(fistList.subList(0, (int)Math.ceil(firstListSize * (1-populationEquality) )));
            Collections.shuffle(subItems);
            BestPrecinct = subItems.get(0);

        }
        else if((politicalFairness != 0) && (populationEquality == 0) && (compactness == 0)){
            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> fistList = politicalFairness1.fairness(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = fistList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(fistList.subList(0, (int)Math.ceil(firstListSize * (1-politicalFairness) )));
            Collections.shuffle(subItems);
            BestPrecinct = subItems.get(0);

        }



        else if((compactness >= politicalFairness) && (politicalFairness >= populationEquality)){  //compactness > political > population
            Compactness compactness1 = new Compactness();
            List<Precinct> fistList = compactness1.compactness(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = fistList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(fistList.subList(0, (int)Math.ceil(firstListSize * (1-compactness) )));
            Set<Precinct> pass1 = new HashSet<>(subItems);

            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> secondList = politicalFairness1.fairness(state,pass1,currentDistrict,seedPrecinct);
            double secondListSize = secondList.size();
            List<Precinct> subItems2 = new ArrayList<Precinct>(secondList.subList(0, (int)Math.ceil(secondListSize * (1-politicalFairness) )));
            Set<Precinct> pass2 = new HashSet<>(subItems2);

            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> thirdList = populationEquality1.populationEquality(state,pass2,currentDistrict,seedPrecinct);
            List<Precinct> subItems3 = new ArrayList<Precinct>(thirdList.subList(0, (int)Math.ceil(thirdList.size() * (1- populationEquality) )));
            Collections.shuffle(thirdList);
            BestPrecinct = thirdList.get(0);
        }

        else if((compactness >= populationEquality) && (populationEquality >= politicalFairness)){ //compactness > populationEquality > political
            Compactness compactness1 = new Compactness();
            List<Precinct> fistList = compactness1.compactness(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = fistList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(fistList.subList(0, (int)Math.ceil(firstListSize * (1-compactness) )));
            Set<Precinct> pass1 = new HashSet<>(subItems);


            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> secondList = populationEquality1.populationEquality(state,pass1,currentDistrict,seedPrecinct);
            double secondListSize = secondList.size();
            List<Precinct> subItems2 = new ArrayList<Precinct>(secondList.subList(0, (int)Math.ceil(secondListSize * (1-populationEquality) )));
            Set<Precinct> pass2 = new HashSet<>(subItems2);

            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> thirdList = politicalFairness1.fairness(state,pass2,currentDistrict,seedPrecinct);

            List<Precinct> subItems3 = new ArrayList<Precinct>(thirdList.subList(0, (int)Math.ceil(thirdList.size() * (1- politicalFairness) )));
            Collections.shuffle(thirdList);
            BestPrecinct = thirdList.get(0);
        }


        else if((populationEquality >= compactness) &&(compactness >= politicalFairness)){  //population > compactness > political
            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> firstList = populationEquality1.populationEquality(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = firstList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(firstList.subList(0, (int)Math.ceil(firstListSize * (1-populationEquality))));
            Set<Precinct> pass1 = new HashSet<>(subItems);

            Compactness compactness1 = new Compactness();
            List<Precinct> secondList = compactness1.compactness(state,pass1,currentDistrict,seedPrecinct);
            double secondListSize = secondList.size();
            List<Precinct> subItems2 = new ArrayList<Precinct>(secondList.subList(0, (int)Math.ceil(secondListSize * (1-compactness))));
            Set<Precinct> pass2 = new HashSet<>(subItems2);

            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> thirdList = politicalFairness1.fairness(state,pass2,currentDistrict,seedPrecinct);
            List<Precinct> subItems3 = new ArrayList<Precinct>(thirdList.subList(0, (int)Math.ceil(thirdList.size() * (1- politicalFairness) )));
            Collections.shuffle(thirdList);
            BestPrecinct = thirdList.get(0);
        }
        else if((populationEquality >= politicalFairness) && (politicalFairness >= compactness)){ // population > political > compactness

            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> firstList = populationEquality1.populationEquality(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = firstList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(firstList.subList(0, (int)Math.ceil(firstListSize * (1-populationEquality))));
            Set<Precinct> pass1 = new HashSet<>(subItems);

            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> secondList = politicalFairness1.fairness(state,pass1,currentDistrict,seedPrecinct);
            double secondListSize = secondList.size();
            List<Precinct> subItems2 = new ArrayList<Precinct>(secondList.subList(0, (int)Math.ceil(secondListSize * (1-politicalFairness))));
            Set<Precinct> pass2 = new HashSet<>(subItems2);

            Compactness compactness1 = new Compactness();
            List<Precinct> thirdList = compactness1.compactness(state,pass2,currentDistrict,seedPrecinct);
            List<Precinct> subItems3 = new ArrayList<Precinct>(thirdList.subList(0, (int)Math.ceil(thirdList.size() * (1- compactness) )));
            Collections.shuffle(thirdList);
            BestPrecinct = thirdList.get(0);

        }
        else if((politicalFairness >= compactness)&& (compactness >= populationEquality) ){ //political > compactness > population

            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> firstList = politicalFairness1.fairness(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = firstList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(firstList.subList(0, (int)Math.ceil(firstListSize * (1-politicalFairness))));
            Set<Precinct> pass1 = new HashSet<>(subItems);

            Compactness compactness1 = new Compactness();
            List<Precinct> secondList = compactness1.compactness(state,pass1,currentDistrict,seedPrecinct);
            double secondListSize = secondList.size();
            List<Precinct> subItems2 = new ArrayList<Precinct>(secondList.subList(0, (int)Math.ceil(secondListSize * (1-compactness))));
            Set<Precinct> pass2 = new HashSet<>(subItems2);

            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> thirdList = populationEquality1.populationEquality(state,pass2,currentDistrict,seedPrecinct);
            List<Precinct> subItems3 = new ArrayList<Precinct>(thirdList.subList(0, (int)Math.ceil(thirdList.size() * (1- populationEquality) )));
            Collections.shuffle(thirdList);
            BestPrecinct = thirdList.get(0);
        }
        else if((politicalFairness >= populationEquality)&& (populationEquality >= compactness)){ // political > population > compactness
            PoliticalFairness politicalFairness1 = new PoliticalFairness();
            List<Precinct> firstList = politicalFairness1.fairness(state,possibleNeighnors,currentDistrict,seedPrecinct);
            double firstListSize = firstList.size();
            List<Precinct> subItems = new ArrayList<Precinct>(firstList.subList(0, (int)Math.ceil(firstListSize * (1-politicalFairness))));
            Set<Precinct> pass1 = new HashSet<>(subItems);

            PopulationEquality populationEquality1 = new PopulationEquality();
            List<Precinct> secondList = populationEquality1.populationEquality(state,pass1,currentDistrict,seedPrecinct);
            double secondListSize = secondList.size();
            List<Precinct> subItems2 = new ArrayList<Precinct>(secondList.subList(0, (int)Math.ceil(secondListSize * (1-populationEquality))));
            Set<Precinct> pass2 = new HashSet<>(subItems2);

            Compactness compactness1 = new Compactness();
            List<Precinct> thirdList = compactness1.compactness(state,pass2,currentDistrict,seedPrecinct);
            List<Precinct> subItems3 = new ArrayList<Precinct>(thirdList.subList(0, (int)Math.ceil(thirdList.size() * (1- compactness) )));
            Collections.shuffle(thirdList);
            BestPrecinct = thirdList.get(0);
        }
        return  BestPrecinct;

    }
}
