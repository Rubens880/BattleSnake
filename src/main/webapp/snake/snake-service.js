import LoginService from "../login-service.js";


export default class SnakeService {


    async getSnake() {
        //TODO: haal deze data van de server
        //
        const loginService = new LoginService();
        /* Controleert of de gebruiker een geldig jwt token heeft voordat de get wordt verstuurd.
        * Als de gebruiker geen jwt token heeft of geen geldige jwt token heeft wordt de gebruiker naar de homepage gestuurd.
        * Als de gebruiker een geldig jwt token heeft wordt de get methode uitgevoerd.
        */
        return loginService.getUser().then(user => {
            if (!user) {

                window.location.replace("/");
                return null;
            } else {

                const options = {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' +  window.localStorage.getItem("myJWT")}
                }

                return fetch("/restservices/snake", options)
                    .then((response) => {
                        if (response.ok) {
                            return response.json();
                        } else throw "Something went wrong!";
                    })
                    .then((myJson) => {
                        console.log(myJson);
                        return Promise.resolve({

                            author: "de dapper student",
                            color: myJson.color,
                            head: myJson.head,
                            tail: myJson.tail,
                        });
                    })
            }
        })

    }

    //Update de snake met meegegeven values
    async updateSnake(updatedSnake) {
        //TODO: update je slang aan de server-kant met de nieuwe gegevens


        const options = {
            method: "PUT",
            headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' +  window.localStorage.getItem("myJWT")},
            body: JSON.stringify(updatedSnake)
        }

        fetch("/restservices/snake", options)
            .then((response) => {
                if (response.ok) {
                    console.log("gelukt");
                }
            })

        return Promise.resolve();
    }

}
