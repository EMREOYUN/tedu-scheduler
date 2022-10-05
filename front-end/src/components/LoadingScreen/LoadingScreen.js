import React from "react";
import "./LoadingScreen.scss"

const LoadingScreen = ({ isLoading }) => {


    return (
        <div className={"loading-screen" + (isLoading ? " open" : "")}>
            <div
                className="boxes">
                <div
                    className="box">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <div
                    className="box">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <div
                    className="box">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <div
                    className="box">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
            <div className={"text"}>
            Loading...<br />If it takes longer than 10 seconds, the server might be down.<br />Access from outside of Türkiye is not allowed. In that case, please use <a href="https://remote.tedu.edu.tr/sites/default/files/content_files/teduglobalprotectvpn_en.pdf">school VPN</a> to connect.
            </div>
        </div>
    )

}

export default LoadingScreen
