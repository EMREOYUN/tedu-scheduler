import React, {useRef, useState} from "react";
import "./Fetch.scss";
import {Button, Input} from "@material-ui/core";
import {fetchDataFromCsv} from "../../service/FetchService"
import cogoToast from "cogo-toast";

const Fetch = () => {
    const [key, setKey] = useState("");
    const [file, setFile] = useState("");
    const [semesterName, setSemesterName] = useState("");
    const [semesterYear, setSemesterYear] = useState("");
    const [semesterCode, setSemesterCode] = useState("");

    const fetchData = () => {
        fetchDataFromCsv(key, file, semesterName, semesterYear, semesterCode).then((res) => {
            cogoToast.info(res.data);
        }).catch((error) => {
            cogoToast.error(error.response.data);
        });
    }

    return <div className={"fetch-container"}>
        <div className={"row"}>
            <div className={"text"}>Key</div>
            <Input className={"input"} value={key} onChange={e => setKey(e.target.value)} />
        </div>
        <div className={"row"}>
            <div className={"text"}>File</div>
            <Input className={"input"} onChange={e => setFile(e.target.files[0])} type="file"/>
        </div>
        <div className={"row"}>
            <div className={"text"}>Semester Name(Spring 2022-2023)</div>
            <Input className={"input"} value={semesterName} onChange={e => setSemesterName(e.target.value)} />
        </div>
        <div className={"row"}>
            <div className={"text"}>Semester Year (2022)</div>
            <Input className={"input"} value={semesterYear} onChange={e => setSemesterYear(e.target.value)} />
        </div>
        <div className={"row"}>
            <div className={"text"}>Semester Code (001)</div>
            <Input className={"input"} value={semesterCode} onChange={e => setSemesterCode(e.target.value)} />
        </div>
        <div className={"row"}>
            <button className={"button"} type={"submit"} onClick={fetchData}> Submit </button>
        </div>
    </div>
}

export default Fetch