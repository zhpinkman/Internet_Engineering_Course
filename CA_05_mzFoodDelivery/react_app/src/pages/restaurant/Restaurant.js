import React from "react";
import Header from "../general/Header";
import Footer from "../general/Footer";

export default class Restaurant extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            queryParams: new URLSearchParams(window.location.search),
            restaurantId: new URLSearchParams(window.location.search).get("id")
        };

        if(this.state.restaurantId == null){
            this.props.history.push("/");
        }
    }

    

    componentDidMount() {

    }

    render() {
        return (
            <div>
                <Header/>
                <Footer/>
                {this.state.restaurantId}
            </div>
        );
    }

}