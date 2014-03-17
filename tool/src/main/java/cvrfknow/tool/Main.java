package cvrfknow.tool;

import org.apache.commons.cli.Options;

public class Main {

    public Main(String[] args){

        Options options = new Options();
        options.addOption("f", false, "the source cvrl file conforming to http://www.icasi.org/CVRF/schema/cvrf/1.1");
    }

    public static void main(String[] args){

    }
}
