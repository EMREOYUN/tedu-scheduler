import React from "react";
import "./CourseTable.scss";
import CourseSelectionTab from "../CourseSelectionTab/CourseSelectionTab";
import CourseFilter from "../CourseFilter/CourseFilter";

const CourseTable = ({
                         advancedFilter,
                         updateAdvancedFilter,
                         setIsLoading,
                         timetableData,
                         updateTimetableData,
                         isTutorialOpen,
                         setIsTutorialOpen,
                         selectedSemester,
                         setSelectedSemester
                     }) => {

    return (
        <div className={"left-table"}>
            <CourseSelectionTab
                {...{
                    advancedFilter,
                    updateAdvancedFilter,
                    setIsLoading,
                    timetableData,
                    updateTimetableData,
                    isTutorialOpen,
                    setIsTutorialOpen,
                    selectedSemester,
                    setSelectedSemester
                }}
            />
            <CourseFilter
                {...{
                    advancedFilter,
                    updateAdvancedFilter,
                    timetableData,
                    updateTimetableData,
                    selectedSemester,
                    setSelectedSemester
                }}
            />
        </div>
    )
}

export default CourseTable