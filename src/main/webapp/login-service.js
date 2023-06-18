export default class LoginService {
    isLoggedIn() {
        //TODO: hoe ga je bepalen of iemand ingelogd is (geweest)?
        if (window.localStorage.getItem("myJWT") === null) {
            console.log("test")
            return false;
        }
        return true;
    }

    login(user, password) {
        //TODO: inloggen met POST
        let data = {
            'username': user,
            'password': password
        }
        console.log(data);
        return fetch("/restservices/authentication", {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            if (response.ok) return response.json();
                else throw "Wrong username/password"
        }).then((myJson) => {
            window.localStorage.setItem("myJWT", myJson.JWT);
        })
    }

    getUser() {
        //TODO: deze GET method test je token op server-side problemen. Je kunt client-side op zich wel 'ingelogd' zijn
        //maar het zou altijd zomaar kunnen dat je token verlopen is, of dat er server-side iets anders aan de hand is.
        //Dus het is handig om te checken met een -echte fetch- of je login-token wel echt bruikbaar is.
        return fetch("")

        return Promise.resolve(true);
    }

    logout() {
        //TODO: hoe ga je eigenlijk iemand 'uitloggen'?
        // if (window.localStorage.getItem("myJWT") !== null) {
        //     window.localStorage.removeItem("myJWT");
        // }
        console.log("Kaas")
        return Promise.resolve(localStorage.removeItem("myJWT"));
    }
}
