import LoginService from "../login-service.js";

export default class GamesService {

    //Controleert via de LoginService of de gebruiker is ingelogd.
    async checkLoggedIn() {
        const loginService = new LoginService();

        return loginService.getUser().then(user => {
            if (!user) {
                window.location.replace("/");
                return false;
            }
            return true;
        })
        }

    async getGameIds() {
        //TODO: fetch alle games van de de service, idealiter zonder alle details
        //TODO: ids van games opgeven via een fetch
        const options = {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' +  window.localStorage.getItem("myJWT")}
        }

        return fetch("/restservices/games", options)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                }
            })

    }

    async getReplay(gameId) {
        //TODO: fetch de details van een enkele game. Let wel, het staat vrij wat voor informatie je precies toont
        //zolang je maar laat zien dat je data kunt opslaan over meerdere zetten heen. Dus deze dummy-data is puur
        //ter illustratie.
        const options = {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' +  window.localStorage.getItem("myJWT")}
        }


        return fetch(`/restservices/games/game/${gameId}`, options)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                }
            })

    }

    async removeReplay(gameId) {
        //TODO: gebruik fetch om een enkele game (bij de server) te deleten
        const options = {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' +  window.localStorage.getItem("myJWT")}
        }

        return fetch(`/restservices/games/game/${gameId}`, options)
            .then((response) => {
                if (response.ok) {
                    console.log(response.json());
                }
            })
    }
}
