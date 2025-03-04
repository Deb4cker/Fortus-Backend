package com.pmnato.fortus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pmnato.fortus.commons.constants.EntityRoutes.HOME_ROUTE;

@RestController
public class HomeController
{
    @GetMapping(HOME_ROUTE)
    public String home(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Fortuss APII️</title>
                </head>
                <body>
                    <h1>Fortus API Back-End</h1>
                </body>
                </html>
               \s""";
    }
}
