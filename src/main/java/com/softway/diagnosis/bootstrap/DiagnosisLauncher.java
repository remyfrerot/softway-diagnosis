package com.softway.diagnosis.bootstrap;

import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.infrastructure.adapter.input.console.DiagnosisConsoleApplication;

import java.util.Collection;

public class DiagnosisLauncher {

    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: DiagnosisLauncher <health index>");
            System.exit(1);
        }

        try {
            final HealthIndex healthIndex = new HealthIndex(Integer.parseInt(args[0]));
            final DiagnosisConsoleApplication diagnosisConsoleApplication = new DiagnosisConsoleApplication();
            final Collection<MedicalUnit> foundMedicalUnits = diagnosisConsoleApplication.findMedicalUnitsByHealthIndex(healthIndex);
            System.out.println(DiagnosisConsoleApplication.toString(foundMedicalUnits));
        } catch (final NumberFormatException e) {
            System.err.println("Health index must be an integer: " + args[0]);
            System.exit(1);
        } catch (final PathologyNotFoundException e) {
            throw new RuntimeException("Error while diagnosing pathology", e);
        } catch (final NoSuchMedicalUnitForThesePathologiesException e) {
            throw new RuntimeException("Error while finding a medical unit", e);
        }
    }
}