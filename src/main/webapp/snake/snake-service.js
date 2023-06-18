export default class SnakeService {
    async getSnake() {
        //TODO: haal deze data van de server
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
