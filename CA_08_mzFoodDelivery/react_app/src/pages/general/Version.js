import React, { Component } from 'react'
import {VersionService} from "../../services/VersionService";


class Version extends Component {
    constructor(props) {
        super(props);
        this.setState({
            backVersion: "Asking Server..."
        })

    }

    componentDidMount() {
        VersionService.getBackendVersion().then(version => {
            this._isMounted && this.setState({
                backVersion: version,
            });
            console.log(version);
        })
    }

    render() {
        return (
            <div className="d-flex justify-content-center align-items-center mt-5" dir="ltr">
                Front Version: 1.0
                <br/>
                Back Version: {this.state.backVersion}
            </div>
        )
    }
}

export default Version;