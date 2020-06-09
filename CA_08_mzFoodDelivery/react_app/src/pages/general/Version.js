import React, { Component } from 'react'
import {VersionService} from "../../services/VersionService";
import {FRONT_VERSION} from "../../config/config";


class Version extends Component {
    constructor(props) {
        super(props);
        this.state = {
            backVersion: "Asking Server..."
        };
    }

    componentDidMount() {
        VersionService.getBackendVersion().then(version => {
            console.log(version);
            this.setState({
                backVersion: version,
            });
        })
    }

    render() {
        return (
            <div className="d-flex justify-content-center align-items-center mt-5" dir="ltr">
                Front Version: {FRONT_VERSION}
                <br/>
                Back Version: {this.state.backVersion}
            </div>
        )
    }
}

export default Version;