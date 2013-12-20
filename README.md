jcompass-maven-plugin
=====================

Maven plugin which allow easy compilation of sass files using the Compass CSS Authoring Framework

# Usage

    <plugin>
        <groupId>com.arcanix.maven.plugin.jcompass</groupId>
        <artifactId>jcompass-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
            <configFile>${basedir}/src/main/webapp/config.rb</configFile>
        </configuration>
        <executions>
            <execution>
                <phase>generate-sources</phase>
                <goals>
                    <goal>update-stylesheets</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

## Optional parameters

If you wish to skip the execution of jcompass plugin entirely, you can use the property "jcompass.skip" as follow:

    mvn jcompass:update-stylesheets -Djcompass.skip=true

# For example

    - src/main/webapp/
        - config.rb
        - css
            - test1.css
            - test2.css
        - images/
            - sprites/
                - element/
                - element-<hash>.png
        - sass/
            - test1.scss
            - test2.scss
