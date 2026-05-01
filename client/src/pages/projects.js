import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getProjects } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Project = (project, active) => {
   return (
       <div>
           <div>{project.name}</div>
           {active === true ? ProjectBody(project) : null}
       </div>
   )
}

const ProjectBody = (project) => {
    let styles = project.qualifications.map((qualification) => project.missingQualifications.includes(qualification) ? { backgroundColor: 'Tomato'}: { backgroundColor: 'LightGreen'})
    return (
        <div style={grayContainerStyle}>
            <div>
                Name: {project.name}
            </div>

            <div>
                Size: {project.size}
            </div>

            <div>
                Status: {project.status}
            </div>

            <div>
                Workers: <ClickList list={project.workers} styles={darkGrayContainerStyle} path="/workers" />
            </div>

            <div>
                Qualifications: <ClickList list={project.qualifications} styles={styles} path="/qualifications" />
            </div>

            
            
        </div>
    )
}

const Projects = () => {
   const [projects, setProjects] = useState([])
    useEffect(() => { getProjects().then(setProjects) }, [])
    const active = LocationID('projects', projects, 'name')
    return (
        <div style={pageStyle}>
            <h1>
                Company Projects
            </h1>
            <ClickList active={active} list={projects} item={Project} path='/projects' id='name' />
        </div>
    )
}

export default Projects